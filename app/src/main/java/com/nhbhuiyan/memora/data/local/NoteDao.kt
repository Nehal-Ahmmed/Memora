package com.nhbhuiyan.memora.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("select * from notes")
    fun getAllNotes(): Flow<List<LocalNote>>

    @Insert
    suspend fun insertNote(note: LocalNote)

    @Update
    suspend fun updateNote(note: LocalNote)

    @Delete
    suspend fun deleteNote(note: LocalNote)

    @Query("select * from notes where title like '%' || :query || '%' OR description like '%' || :query || '%' ")
    fun searchNotes(query: String): Flow<List<LocalNote>>

//    // Observe all notes (automatic updates)
//    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
//    fun getAllNote(): Flow<List<LocalNote>>

}