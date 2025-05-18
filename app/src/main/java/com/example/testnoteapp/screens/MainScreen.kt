package com.example.testnoteapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testnoteapp.components.InputField
import com.example.testnoteapp.components.NoteRow
import com.example.testnoteapp.components.SaveButton
import com.example.testnoteapp.model.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Composable
fun MainScreen (
    noteList: List<Note>,
    addNote: (Note) -> Unit,
    removeNote: (Note) -> Unit,
    deleteAll: () -> Unit,
) {

    val title = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
        ,
    ) {
        InputField(value = title, label = "Title", onValueChange = {
            if (it.all { char -> char.isDefined() }) { title.value = it }
        })
        InputField(value = desc, label = "Description", onValueChange = {
            if (it.all { char -> char.isDefined() }) { desc.value = it }
        })
        Row (
            modifier = Modifier.width(150.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            SaveButton(
                onClick = {
                    if (title.value.isNotBlank()) {
                        addNote(
                            Note(
                                title = title.value,
                                desc = desc.value
                            )
                        )
                        title.value = ""; desc.value = ""
                    }
                }
            )
            OutlinedButton(onClick = {deleteAll()}) { Text("X") }
        }

        HorizontalDivider(thickness = 1.dp, modifier = Modifier
            .widthIn(200.dp, 250.dp)
            .padding(top = 3.dp)
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
            ,

        ) {
//            items(noteList) { item ->
//                NoteRow(
//                    note = noteList[item],
//                    onClick = { removeNote(item) }
//                )
            noteList.forEach { note ->
                NoteRow(
                    note = note,
                    onClick = { removeNote(note) }
                )
            }
        }
    }

}