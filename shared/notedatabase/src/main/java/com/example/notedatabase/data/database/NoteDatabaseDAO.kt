package com.example.notedatabase.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDatabaseDAO {

	@Insert
	suspend fun create(note: NoteDBEntity): Long

	@Update
	suspend fun update(note: NoteDBEntity)

	@Delete
	suspend fun delete(note: NoteDBEntity)

	@Query("SELECT * FROM notes")
	suspend fun getAll(): List<NoteDBEntity>

	@Query("SELECT * FROM notes WHERE id = :id")
	suspend fun getById(id: String): NoteDBEntity
}