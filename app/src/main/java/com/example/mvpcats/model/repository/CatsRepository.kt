package com.example.mvpcats.model.repository

import com.example.mvpcats.model.api.CatsApi
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.database.CatsDatabase
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.util.Constants
import io.reactivex.Single

class CatsRepository : MainContract.Repository {

    private val catsDatabase = CatsDatabase.INSTANCE
    private val catsDao = catsDatabase?.catsDao()

    fun getFavouriteCats(): ArrayList<Cats> {
        return catsDao?.getFavoritesCats()!!
    }

    fun insertFavouriteCat(url: String){
        catsDao?.insertCatsToFavourites(url)
    }

    override fun loadCats(
        format: String,
        order: String,
        size: String,
        limit: Int,
        page: Int
    ): Single<CatsModel> {
        return CatsApi.create().getCats(
            Constants.API_KEY,
            size = size,
            order = order,
            limit = limit,
            page = page,
            format = format
        )
    }
}