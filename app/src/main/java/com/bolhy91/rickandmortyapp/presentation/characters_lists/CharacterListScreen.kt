package com.bolhy91.rickandmortyapp.presentation.characters_lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bolhy91.rickandmortyapp.ui.components.CharacterItem
import com.bolhy91.rickandmortyapp.ui.components.InputSearch
import com.bolhy91.rickandmortyapp.ui.theme.PurpleGrey40
import androidx.compose.ui.graphics.Color
import com.bolhy91.rickandmortyapp.ui.components.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onClickItem: (Int) -> Unit
) {
    val state = viewModel.state
    Scaffold(
        topBar = {
            Column {
                Text(
                    text = "Rick And Morty",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                InputSearch {}
            }
        },
        bottomBar = {
            BottomBar(
                showPrevious = state.value.showPrevious,
                showNext = state.value.showNext,
                onPreviousPressed = { viewModel.getCharacters(increase = false) },
                onNextPressed = { viewModel.getCharacters(true) }
            )
        },
        modifier = Modifier.padding(16.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(state.value.characters) { character ->
                    CharacterItem(character = character) {
                        onClickItem(it)
                    }
                    Divider(color = PurpleGrey40, modifier = Modifier.padding(5.dp))
                }
            }
        }
    }

}