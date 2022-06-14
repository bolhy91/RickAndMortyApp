package com.bolhy91.rickandmortyapp.presentation.characters_detail

import com.bolhy91.rickandmortyapp.domain.model.Character

data class CharacterDetailState(
    val character: Character? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
