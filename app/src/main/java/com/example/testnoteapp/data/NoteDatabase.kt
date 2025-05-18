package com.example.testnoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.testnoteapp.model.Note
import com.example.testnoteapp.util.DateConverter
import com.example.testnoteapp.util.UUIDConverter

@Database(entities = [Note::class],version = 2, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}