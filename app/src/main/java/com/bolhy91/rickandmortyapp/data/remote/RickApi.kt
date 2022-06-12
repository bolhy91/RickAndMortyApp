package com.bolhy91.rickandmortyapp.data.remote

import com.bolhy91.rickandmortyapp.data.remote.dto.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickApi {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String?
    ): CharacterListDto
}