package com.bolhy91.rickandmortyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bolhy91.rickandmortyapp.domain.model.Character

@Dao
interface RickDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterEntity: List<CharacterEntity>)

    @Query(
        """
        SELECT * FROM characters
        WHERE LOWER(name) LIKE '%' || LOWER(:name) || '%' OR
        page == :page
    """
    )
    suspend fun searchCharacters(page: Int, name: String?): List<CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clearCharacters()

    @Query(
        """
        SELECT * FROM characters as c WHERE c.id =:id
    """
    )
    suspend fun getCharacterById(id: Int): CharacterEntity
}
