package com.bolhy91.rickandmortyapp.presentation.characters_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bolhy91.rickandmortyapp.domain.model.Character
import com.bolhy91.rickandmortyapp.domain.model.CharacterStatus
import com.bolhy91.rickandmortyapp.ui.theme.Pink80
import com.bolhy91.rickandmortyapp.ui.theme.RickAndMortyAppTheme

@Composable
fun CharacterDetailScreen(character: Character) {
    RickAndMortyAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(25.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp),
                        contentDescription = "arrow back"
                    )
                    Text(
                        text = "Rick And Morty",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }

                CharacterContent(character = character)

            }
        }
    }
}

@Composable
fun CharacterContent(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(120.dp)
                        .background(Color.Transparent)
                        .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(character.image),
                        contentDescription = "null",
                        modifier = Modifier
                            .shadow(
                                0.dp,
                                shape = RoundedCornerShape(16.dp),
                                true
                            ),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                }
                Badge(
                    containerColor = when (character.status) {
                        CharacterStatus.ALIVE.status -> {
                            Pink80
                        }
                        CharacterStatus.UNKNOWN.status -> {
                            Color.Gray
                        }
                        else -> Color.Red
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(100.dp)
                        .height(30.dp),
                ) {
                    Text(
                        text = character.status,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = character.name,
                    modifier = Modifier
                        .align(Alignment.Start),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = "Species: ${character.species}",
                    modifier = Modifier
                        .align(Alignment.Start),
                    style = TextStyle(color = Color.Black, fontSize = 16.sp),
                )
                Text(
                    text = "Location: ${character.location}",
                    style = TextStyle(color = Color.Black, fontSize = 14.sp),
                )
                Text(
                    text = "Gender: ${character.gender}",
                    style = TextStyle(color = Color.Black, fontSize = 14.sp),
                )

                Text(
                    text = "Created: ${character.created}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    ),
                )
            }

        }
    }
}

@Preview
@Composable
fun CharacterDetailPreview() {
    val character = Character(
        1,
        "Rick Morty",
        "unknown",
        "Robot",
        "Male",
        "Ciudad de Panama",
        "https://rickandmortyapi.com/api/character/avatar/101.jpeg",
        "4/8/22"
    )
    CharacterDetailScreen(character = character)
}