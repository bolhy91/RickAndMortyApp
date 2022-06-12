package com.bolhy91.rickandmortyapp.presentation.characters_lists

sealed class CharacterListEvent {
    data class OnSearchQueryChange(val name: String): CharacterListEvent()
    data class onPreviousPressed(val increase: Boolean): CharacterListEvent()
    data class onNextPressed(val increase: Boolean): CharacterListEvent()
}
