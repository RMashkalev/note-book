package com.example.notedatabase.data.database

import android.graphics.Color
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Entity(tableName = "notes")
data class NoteDBEntity(
	@PrimaryKey(autoGenerate = true) val id: Long,
	val title: String,
	val description: String,
	val firstColor: Long,
	val secondColor: Long,
)

@Database(entities = [NoteDBEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

	abstract fun noteDatabaseDAO(): NoteDatabaseDAO
}