package com.example.testnoteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class Note (
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "note_title")
    val title: String,
    @ColumnInfo(name = "note_desc")
    val desc: String,
    @ColumnInfo(name = "note_date")
    val entryDate: Date = Date.from(Instant.now()),
    @ColumnInfo(name = "note_iscompl")
    val isCompleted: Boolean = false,
)