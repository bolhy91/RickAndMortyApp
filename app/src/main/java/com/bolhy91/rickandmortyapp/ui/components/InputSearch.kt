package com.bolhy91.rickandmortyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputSearch(
    onInputValue: (String) -> Unit
) {
    var inputValue by remember { mutableStateOf("") }
    TextField(
        value = inputValue,
        onValueChange = {
            inputValue = it
            onInputValue(inputValue)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search character",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
            )
        },
        label = { Text(text = "Search character") },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
        singleLine = true
    )
}