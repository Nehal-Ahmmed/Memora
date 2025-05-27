package com.nhbhuiyan.memora.data.Repository

import android.content.Context
import com.nhbhuiyan.memora.data.local.AppDatabase
import com.nhbhuiyan.memora.data.local.LocalNote
import kotlinx.coroutines.flow.Flow

class NoteRepository(context: Context) {
    private val noteDao= AppDatabase.getDatabse(context).noteDao()

    // Get all notes as Flow
    fun getAllNotes() : Flow<List<LocalNote>> = noteDao.getAllNotes()

    // Add new note
    suspend fun addNote(note: LocalNote){
        noteDao.insertNote(note)
    }
    suspend fun deleteNote(note: LocalNote){
        noteDao.deleteNote(note)
    }
    suspend fun updateNote(note: LocalNote){
        noteDao.updateNote(note)
    }
    fun searchNotes(query: String):Flow<List<LocalNote>>{
        return if (query.isBlank()){
            noteDao.getAllNotes()
        }else{
            noteDao.searchNotes(query)
        }
    }
}