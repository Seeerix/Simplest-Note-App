package com.example.testnoteapp.components

import android.view.RoundedCorner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.testnoteapp.model.Note
import com.example.testnoteapp.util.formatDate

@Composable
fun NoteRow (
    note: Note,
    onClick: () -> Unit = {}
) {
    Surface (
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp, 80.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(topStart = 25.dp, bottomEnd = 25.dp))
            .padding(3.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline))
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(note.title)
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(note.desc)
                Text(formatDate(note.entryDate.time))
            }
        }
    }

}