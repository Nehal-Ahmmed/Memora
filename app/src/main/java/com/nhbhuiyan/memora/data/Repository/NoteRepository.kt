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
}