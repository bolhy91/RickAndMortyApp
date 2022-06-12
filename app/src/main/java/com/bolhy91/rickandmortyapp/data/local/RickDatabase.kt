package com.bolhy91.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class RickDatabase : RoomDatabase() {
    abstract fun dao(): RickDao
}