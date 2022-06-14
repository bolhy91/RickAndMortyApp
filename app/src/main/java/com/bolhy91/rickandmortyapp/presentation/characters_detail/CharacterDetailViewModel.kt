package com.bolhy91.rickandmortyapp.presentation.characters_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolhy91.rickandmortyapp.domain.repository.CharacterRepository
import com.bolhy91.rickandmortyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    private val _state: MutableState<CharacterDetailState> = mutableStateOf(CharacterDetailState())
    val state: State<CharacterDetailState> = _state

    init {
        viewModelScope.launch {
            val characterId = savedStateHandle.get<Int>("id") ?: return@launch
            characterRepository.getCharacterById(characterId, false).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message,
                            character = null,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = result.isLoading,
                            character = null
                        )
                    }
                    is Resource.Success -> {
                        //Ver el proceso de carga
                        //delay(500L)
                        _state.value = _state.value.copy(
                            character = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }

        }
    }
}