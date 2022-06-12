package com.bolhy91.rickandmortyapp.domain.repository

import com.bolhy91.rickandmortyapp.domain.model.Character
import com.bolhy91.rickandmortyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(page: Int, name: String?, fetchFromRemote: Boolean): Flow<Resource<List<Character>>>
}