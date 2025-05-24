package com.nhbhuiyan.memora.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nhbhuiyan.memora.data.Repository.NoteRepository
import com.nhbhuiyan.memora.data.local.LocalNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository= NoteRepository(application)
    val allNotes: Flow<List<LocalNote>> = repository.getAllNotes()

    fun addNote(title: String, description: String){

        viewModelScope.launch {
            repository.addNote(
                LocalNote(
                    id = UUID.randomUUID().toString(),
                    title=title,
                    description=description
                )
            )
        }

    }
}