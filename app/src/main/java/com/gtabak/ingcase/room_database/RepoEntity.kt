package com.gtabak.ingcase.room_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favorites_table")
data class RepoEntity(
    @ColumnInfo(name = "repo_id")
    var repoId:Int) {
    @PrimaryKey(autoGenerate = true)
    var repoAutoId: Int = 0
}