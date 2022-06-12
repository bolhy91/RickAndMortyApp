package com.bolhy91.rickandmortyapp.data.repository

import android.util.Log
import com.bolhy91.rickandmortyapp.data.mapper.toCharacter
import com.bolhy91.rickandmortyapp.data.remote.RickApi
import com.bolhy91.rickandmortyapp.domain.model.Character
import com.bolhy91.rickandmortyapp.domain.repository.CharacterRepository
import com.bolhy91.rickandmortyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val rickApi: RickApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int, name: String?): Flow<Resource<List<Character>>> {
        return flow {
            try {
                emit(Resource.Loading(isLoading = true))
                val results = rickApi.getCharacters(page, name)
                Log.i("Results", results.toString())
                emit(Resource.Success(data = results.results.map { it.toCharacter() }))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("couldn't load data: ${e.message}"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("couldn't load data http: ${e.message}"))
            }
        }
    }
}