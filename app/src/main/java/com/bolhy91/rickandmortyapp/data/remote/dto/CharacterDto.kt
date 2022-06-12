package com.bolhy91.rickandmortyapp.data.remote.dto

data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDto,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

data class LocationDto(val name: String, val url: String)
data class Origin(val name: String, val url: String)