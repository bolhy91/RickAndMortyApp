package com.bolhy91.rickandmortyapp.data.mapper

import com.bolhy91.rickandmortyapp.data.remote.dto.CharacterDto
import com.bolhy91.rickandmortyapp.domain.model.Character

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        location = location.name,
        image = image,
        created = created
    )
}