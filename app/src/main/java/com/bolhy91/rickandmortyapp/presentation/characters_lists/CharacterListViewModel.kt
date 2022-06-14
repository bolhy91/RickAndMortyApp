package com.bolhy91.rickandmortyapp.presentation.characters_lists

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolhy91.rickandmortyapp.domain.repository.CharacterRepository
import com.bolhy91.rickandmortyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {
    private val _state: MutableState<CharacterListState> = mutableStateOf(CharacterListState())
    val state: State<CharacterListState> = _state

    private var currentPage = 1
    private var searchJob: Job? = null

    init {
        getCharacters()
    }

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            is CharacterListEvent.OnSearchQueryChange -> {
                _state.value = _state.value.copy(searchQuery = event.name)
                if (event.name.isBlank()) currentPage = 1
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCharacters(fetchFromRemote = true)
                }
            }
            is CharacterListEvent.onNextPressed -> {
                currentPage++
                getCharacters(fetchFromRemote = true)
            }
            is CharacterListEvent.onPreviousPressed -> {
                currentPage--
                getCharacters(fetchFromRemote = true)
            }
        }
    }

    private fun getCharacters(
        name: String? = _state.value.searchQuery?.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            val showPrevious = currentPage > 1
            characterRepository.getCharacters(currentPage, name, fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = result.message,
                                characters = emptyList()
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                isLoading = result.isLoading,
                                error = null
                            )
                        }
                        is Resource.Success -> {
                            val showNext = currentPage < result.pages!!
                            delay(500L)
                            result.data?.let { characters ->
                                _state.value = _state.value.copy(
                                    characters = characters,
                                    showPrevious = showPrevious,
                                    showNext = showNext,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                    }
                }
        }
    }
}