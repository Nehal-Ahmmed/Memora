package com.nhbhuiyan.memora.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nhbhuiyan.memora.data.Repository.NoteRepository
import com.nhbhuiyan.memora.data.local.LocalNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository= NoteRepository(application)
    val allNotes: Flow<List<LocalNote>> = repository.getAllNotes()

    //search functionality
    private val _searchQuery= MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    // Combined flow for notes with search
    val notes: Flow<List<LocalNote>> = combine(
        allNotes,
        _searchQuery.debounce(300.milliseconds)){notes,query ->
            if (query.isBlank()){
                notes
            }else{
                notes.filter { note->
                    note.title.contains(query, ignoreCase = true) ||
                    note.description.contains(query, ignoreCase = true)
                }
            }
        }
    // Update search query
    fun onSearchQueryChanged(query: String){
        _searchQuery.value=query
    }

    //logic for adding note
    fun addNote(id: String,title: String, description: String){
        viewModelScope.launch {
            if(id.isNotEmpty()){
                repository.updateNote(LocalNote(id,title,description))
            }else{
                repository.addNote(LocalNote(id =UUID.randomUUID().toString(), title=title, description=description))
            }
        }
    }

    //logic for deleting note
     fun deleteNote(note: LocalNote){
         viewModelScope.launch {
             repository.deleteNote(note)
         }
    }
}