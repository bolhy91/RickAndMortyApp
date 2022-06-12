package com.bolhy91.rickandmortyapp.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val location: String,
    val image: String,
    val created: String
)