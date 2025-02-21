package com.example.notedatabase.data.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Entity
data class NoteDBEntity(
	@PrimaryKey(autoGenerate = true) val id: Int,
	val title: String,
	val description: String,
)

@Database(entities = [NoteDBEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

	abstract fun noteDatabaseDAO(): NoteDatabaseDAO
}