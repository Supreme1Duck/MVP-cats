package com.example.mvpcats.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface CatsDao {

    @Insert
    fun insertCatsToFavourites(cat: Cats) : Completable

    @Query("Select * FROM Cats")
    fun getFavoritesCats() : Observable<List<Cats>>
}