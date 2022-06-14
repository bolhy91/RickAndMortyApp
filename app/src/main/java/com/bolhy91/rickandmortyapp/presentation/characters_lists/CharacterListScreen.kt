package com.bolhy91.rickandmortyapp.presentation.characters_lists

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bolhy91.rickandmortyapp.ui.components.CharacterItem
import com.bolhy91.rickandmortyapp.ui.components.InputSearch
import com.bolhy91.rickandmortyapp.ui.theme.PurpleGrey40
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.bolhy91.rickandmortyapp.ui.components.BottomBar
import com.bolhy91.rickandmortyapp.ui.components.Shimmer
import com.bolhy91.rickandmortyapp.R

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
                InputSearch(onInputValue = { name ->
                    Log.i("SEARCH VALUE: ", name)
                    viewModel.onEvent(CharacterListEvent.OnSearchQueryChange(name))
                })
            }
        },
        bottomBar = {
            BottomBar(
                showPrevious = state.value.showPrevious,
                showNext = state.value.showNext,
                onPreviousPressed = {
                    viewModel.onEvent(
                        CharacterListEvent.onPreviousPressed(false)
                    )
                },
                onNextPressed = {
                    viewModel.onEvent(
                        CharacterListEvent.onNextPressed(true)
                    )
                }
            )
        },
        modifier = Modifier.padding(16.dp)
    ) { paddingValues ->

        state.value.characters.let {
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

        Box(
            modifier = Modifier
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.value.isLoading) {
                Column {
                    repeat(20) {
                        Shimmer(
                            brush = linearGradient(
                                listOf(
                                    Color.Gray.copy(alpha = 0.9f),
                                    Color.Gray.copy(alpha = 0.4f),
                                    Color.Gray.copy(alpha = 0.9f)
                                )
                            )
                        )
                    }
                }
            } else {
                state.value.error?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_default),
                            contentDescription = null
                        )
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }

}