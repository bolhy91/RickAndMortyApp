package com.bolhy91.rickandmortyapp.data.mapper

import com.bolhy91.rickandmortyapp.data.local.CharacterEntity
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

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        location = location,
        image = image,
        created = created
    )
}

fun CharacterDto.toCharacterEntity(page: Int): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        location = location.name,
        image = image,
        created = created,
        page = page
    )
}