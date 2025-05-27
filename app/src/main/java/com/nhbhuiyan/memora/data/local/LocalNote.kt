package com.nhbhuiyan.memora.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class LocalNote(
@PrimaryKey
    val id: String,
    val title: String="",
    val description: String="",
    //val createdAt: Long = System.currentTimeMillis()  // Optional: Track creation time
)
