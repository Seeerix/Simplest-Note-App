package com.example.testnoteapp.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testnoteapp.model.Note
import com.example.testnoteapp.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repo: NoteRepository
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                _noteList.value = listOfNotes
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repo.addNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repo.removeNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repo.updateNote(note) }
    fun deleteAll() = viewModelScope.launch { repo.deleteAllNotes() }
}