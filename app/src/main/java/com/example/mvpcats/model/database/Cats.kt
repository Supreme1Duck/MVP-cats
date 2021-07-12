package com.example.mvpcats.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cats(
    @PrimaryKey(autoGenerate = true)
    val string: String
)