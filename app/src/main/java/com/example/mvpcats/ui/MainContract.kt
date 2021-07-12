package com.example.mvpcats.ui

import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.entity.CatsModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MainContract {

    interface View {
        fun showCats(cats: CatsModel)
    }

    interface Presenter {
        fun onActivityCreated(): CatsModel
        fun onDestroy()
        fun getFavouriteCats(): List<Cats>
        fun insertCat(cat: Cats)
    }

    interface Repository {
        fun loadCats(
            format: String,
            order: String,
            size: String,
            limit: Int,
            page: Int
        ): Single<CatsModel>

        fun insertFavouriteCat(cat: Cats) : Completable

        fun getFavouriteCats(): Observable<List<Cats>>
    }
}