package com.example.testnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testnoteapp.model.Note
import com.example.testnoteapp.screens.MainScreen
import com.example.testnoteapp.screens.NoteViewModel
import com.example.testnoteapp.ui.theme.TestNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestNoteAppTheme {
                App {
                    val noteViewModel = viewModel<NoteViewModel>()

                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp (
    noteViewModel: NoteViewModel
) {
    val noteList = noteViewModel.noteList.collectAsState().value
    MainScreen(
        noteList = noteList,
        addNote = { noteViewModel.addNote(it) },
        removeNote = { noteViewModel.removeNote(it) },
        deleteAll = { noteViewModel.deleteAll() }
    )
}

@Composable
fun App (content: @Composable () -> Unit = {}) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface (
            modifier = Modifier.padding(innerPadding)
        ) {
            content()
        }
    }
}