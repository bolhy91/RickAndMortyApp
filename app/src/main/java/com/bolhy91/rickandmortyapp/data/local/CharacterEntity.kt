package com.bolhy91.rickandmortyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: Int = -1,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val location: String,
    val image: String,
    val created: String,
    val page: Int
)