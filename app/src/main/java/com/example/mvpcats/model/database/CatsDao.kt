package com.example.mvpcats.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatsDao {

    @Insert
    fun insertCatsToFavourites(url : String)

    @Query("Select * FROM Cats")
    fun getFavoritesCats() : ArrayList<Cats>
}