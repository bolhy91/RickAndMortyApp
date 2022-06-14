package com.bolhy91.rickandmortyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun Shimmer(brush: Brush) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .width(90.dp)
                .height(90.dp)
                .shimmer()
                .background(brush)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .shimmer()
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(15.dp)
                    .shimmer()
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(15.dp)
                    .shimmer()
                    .background(brush)
            )
        }
    }
}

@Preview
@Composable
fun ShimmerPreview() {
    Shimmer(brush = linearGradient(
        listOf(
            Color.Gray.copy(alpha = 0.9f),
            Color.Gray.copy(alpha = 0.4f),
            Color.Gray.copy(alpha = 0.9f)
        )
    ))
}