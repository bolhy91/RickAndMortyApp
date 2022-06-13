package com.bolhy91.rickandmortyapp.data.repository

import android.util.Log
import com.bolhy91.rickandmortyapp.data.local.RickDao
import com.bolhy91.rickandmortyapp.data.mapper.toCharacter
import com.bolhy91.rickandmortyapp.data.mapper.toCharacterEntity
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
    private val rickApi: RickApi,
    private val rickDao: RickDao
) : CharacterRepository {
    override suspend fun getCharacters(
        page: Int,
        name: String?,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Character>>> {
        return flow {

            emit(Resource.Loading(isLoading = true))
            val localCharacters = rickDao.searchCharacters(page, name)
            Log.i("PAGE: ", page.toString())
            Log.i("localCharacters: ", localCharacters.toString())
            emit(
                Resource.Success(
                    data = localCharacters.map { it.toCharacter() },
                    pages = 42
                )
            )
            val isLocalEmpty = localCharacters.isEmpty() && name.isNullOrBlank()
            Log.i("isLocalEmpty:", isLocalEmpty.toString())

            val shouldJustLoadFromCache = !isLocalEmpty && !fetchFromRemote
            Log.i("shouldJustLoadFromCache:", shouldJustLoadFromCache.toString())
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteCharacter = try {
                val results = rickApi.getCharacters(page, name)
                results
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("couldn't load data: ${e.message}"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("couldn't load data http: ${e.message}"))
                null
            }

            remoteCharacter?.let { results ->
                val charactersEntity = results.results.map { it.toCharacterEntity(page) }
                val pages = results.info.pages
                rickDao.clearCharacters()
                rickDao.insertCharacters(charactersEntity)
                emit(
                    Resource.Success(
                        data = charactersEntity.map { it.toCharacter() },
                        pages = pages
                    )
                )
                emit(Resource.Loading(false))
            }
        }
    }
}