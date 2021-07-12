package com.example.mvpcats.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cats::class], version = 1)
abstract class CatsDatabase : RoomDatabase() {

    abstract fun catsDao(): CatsDao

    companion object {
        var INSTANCE: CatsDatabase? = null

        fun getAppDataBase(context: Context): CatsDatabase? {
            if (INSTANCE == null) {
                synchronized(CatsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CatsDatabase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}