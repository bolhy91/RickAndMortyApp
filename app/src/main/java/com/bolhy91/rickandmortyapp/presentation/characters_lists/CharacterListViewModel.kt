package com.bolhy91.rickandmortyapp.presentation.characters_lists

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolhy91.rickandmortyapp.domain.repository.CharacterRepository
import com.bolhy91.rickandmortyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {
    private val _state: MutableState<CharacterListState> = mutableStateOf(CharacterListState())
    val state: State<CharacterListState> = _state

    init {
        getCharacters()
    }

    private fun getCharacters(
        page: Int = 1,
        name: String = _state.value.searchQuery,
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            characterRepository.getCharacters(page, name)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                        is Resource.Success -> {
                            result.data?.let { characters ->
                                _state.value = _state.value.copy(characters = characters)
                            }
                        }
                    }
                }
        }
    }
}