package com.bolhy91.rickandmortyapp.presentation.characters_lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bolhy91.rickandmortyapp.ui.components.CharacterItem
import com.bolhy91.rickandmortyapp.ui.components.InputSearch

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onClickItem: (Int) -> Unit
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        InputSearch {}
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(state.value.characters) { character ->
                CharacterItem(character = character) {
                    onClickItem(it)
                }
            }
        }
    }
}