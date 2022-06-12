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
        getCharacters(increase = false)
    }

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            is CharacterListEvent.OnSearchQueryChange -> {
                _state.value = _state.value.copy(searchQuery = event.name)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCharacters(true)
                }
            }
            is CharacterListEvent.onNextPressed -> {
                getCharacters(event.increase)
            }
            is CharacterListEvent.onPreviousPressed ->
                getCharacters(event.increase)
        }
    }

    private fun getCharacters(
        increase: Boolean,
        name: String? = _state.value.searchQuery?.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {

            if (increase) currentPage++ else if (currentPage > 1) currentPage--
            val showPrevious = currentPage > 1
            val showNext = currentPage < 42

            characterRepository.getCharacters(currentPage, name, fetchFromRemote)
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
                                _state.value = _state.value.copy(
                                    characters = characters,
                                    showPrevious = showPrevious,
                                    showNext = showNext
                                )
                            }
                        }
                    }
                }
        }
    }
}