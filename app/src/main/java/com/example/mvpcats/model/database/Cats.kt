package com.example.mvpcats.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cats(
    @PrimaryKey(autoGenerate = true)
    val id: Int ?= null,
    val url: String
) {
    constructor(url: String) : this(null, url = url)
}