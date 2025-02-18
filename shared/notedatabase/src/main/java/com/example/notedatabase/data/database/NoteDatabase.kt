package com.example.notedatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.annotation.Single

@Entity
data class NoteDBEntity(
	@PrimaryKey(autoGenerate = true) val id: Int,
	val title: String,
	val description: String,
)

@Database(entities = [NoteDBEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

	@Single
	abstract fun noteDatabaseDAO(): NoteDatabaseDAO

	companion object {
		@Volatile
		private var INSTANCE: NoteDatabase? = null

		@Single
		fun getInstance(context: Context): NoteDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					NoteDatabase::class.java,
					"NoteDatabase"
				).build()
				INSTANCE = instance
				instance
			}
		}
	}
}