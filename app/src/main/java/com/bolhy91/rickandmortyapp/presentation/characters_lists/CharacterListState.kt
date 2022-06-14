package com.bolhy91.rickandmortyapp.presentation.characters_lists

import com.bolhy91.rickandmortyapp.domain.model.Character

data class CharacterListState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String? = null,
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val error: String? = null
)