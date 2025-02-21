package com.example.notedatabase.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDatabaseDAO {

	@Insert
	suspend fun create(note: NoteDBEntity)

	@Update
	suspend fun update(note: NoteDBEntity)

	@Delete
	suspend fun delete(note: NoteDBEntity)

	@Query("SELECT * FROM NoteDBEntity")
	suspend fun getAll(): List<NoteDBEntity>

	@Query("SELECT * FROM NoteDBEntity WHERE id = :id")
	suspend fun getById(id: String): NoteDBEntity
}