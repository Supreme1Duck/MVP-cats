package com.example.mvpcats.ui

import android.graphics.drawable.BitmapDrawable
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.entity.CatsModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MainContract {

    interface CatsView : MarkerView<CatsModel> {
        override fun showCats(t: CatsModel)
    }

    interface FavouriteCatsView : MarkerView<HashSet<String>> {
        override fun showCats(t: HashSet<String>)
    }

    interface MarkerView<T> {
        fun showCats(t: T)
    }

    interface Presenter {
        fun getCats(): CatsModel
        fun onDestroy()
        fun insertCat(cat: Cats)
        fun downloadImage(bitmapDrawable: BitmapDrawable)
    }

    interface FavouriteCatsPresenter {
        fun getFavouriteCats(): Set<Cats>
        fun onDestroy()
        fun mapperCatsToString(hashSet: HashSet<Cats>): HashSet<String>
    }

    interface Repository {
        fun loadCats(
            format: String,
            order: String,
            size: String,
            limit: Int,
            page: Int
        ): Single<CatsModel>

        fun insertFavouriteCat(cat: Cats): Completable

        fun getFavouriteCats(): Observable<List<Cats>>
    }
}