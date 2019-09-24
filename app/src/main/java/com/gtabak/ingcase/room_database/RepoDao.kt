package com.gtabak.ingcase.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepoDao {

    @Query("SELECT * FROM favorites_table")
    fun getAllData(): List<RepoEntity>

    @Query("SELECT * FROM favorites_table where repo_id = :repo_id")
    fun getRow(repo_id: Int): RepoEntity

    @Insert
    fun insert(repoEntity: RepoEntity)

    @Delete
    fun delete(repoEntity: RepoEntity)
}