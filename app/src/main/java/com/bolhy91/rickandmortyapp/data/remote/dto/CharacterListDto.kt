package com.bolhy91.rickandmortyapp.data.remote.dto

data class CharacterListDto(
    val info: InfoPage,
    val results: List<CharacterDto>
)

data class InfoPage(val count: Int, val pages: Int, val next: String, val prev: String)