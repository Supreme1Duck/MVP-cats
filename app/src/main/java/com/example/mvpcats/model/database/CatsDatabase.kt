package com.example.mvpcats.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cats::class], version = 1, exportSchema = false)
abstract class CatsDatabase : RoomDatabase() {

    abstract fun catsDao(): CatsDao
}