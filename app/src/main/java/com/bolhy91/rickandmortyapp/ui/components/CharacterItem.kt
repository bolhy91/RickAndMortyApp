package com.bolhy91.rickandmortyapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.bolhy91.rickandmortyapp.domain.model.Character
import com.bolhy91.rickandmortyapp.ui.theme.PurpleGrey40

@Composable
fun CharacterItem(
    character: Character,
    onClickItem: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(5.dp)
            .clickable { onClickItem(character.id) }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .background(Color.Transparent)
                    .border(1.dp, PurpleGrey40, RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = character.image,
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
                    style = TextStyle(color = Color.Black, fontSize = 12.sp),
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    CharacterItem(character = Character(1, "", "", "", "", "", "", ""), onClickItem = {})
}