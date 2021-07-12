package com.example.mvpcats.model.repository

import android.content.Context
import com.example.mvpcats.model.api.CatsApi
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.database.CatsDatabase
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.util.Constants
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class CatsRepository(
    context: Context
) : MainContract.Repository {

    private val catsDatabase = CatsDatabase.getAppDataBase(context)
    private val catsDao = catsDatabase?.catsDao()

    override fun getFavouriteCats(): Observable<List<Cats>> {
        return catsDao?.getFavoritesCats()!!
    }

    override fun insertFavouriteCat(cat: Cats) : Completable {
        return catsDao?.insertCatsToFavourites(cat)!!
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