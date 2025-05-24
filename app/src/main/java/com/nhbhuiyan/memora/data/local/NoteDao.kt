package com.nhbhuiyan.memora.data.local

import androidx.room.Dao
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

    @Query("delete from notes where id= :noteId")
    suspend fun deleteNote(noteId: String)
}