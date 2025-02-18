package com.example.notedatabase.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notedatabase.data.model.NoteModel

@Dao
interface NoteDatabaseDAO {

	@Insert
	fun create(note: NoteDBEntity)

	@Update
	fun update(note: NoteDBEntity)

	@Delete
	fun delete(note: NoteDBEntity)

	@Query("SELECT * FROM NoteDBEntity")
	fun getAll(): List<NoteDBEntity>
}