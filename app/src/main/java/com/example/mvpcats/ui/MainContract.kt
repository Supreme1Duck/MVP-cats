package com.example.mvpcats.ui

import com.example.mvpcats.model.entity.CatsModel
import io.reactivex.Single

class MainContract {

    interface View {
        fun showCats(cats : CatsModel)
    }

    interface Presenter {
        fun onActivityCreated() : CatsModel
        fun onDestroy()
    }

    interface Repository {
        fun loadCats(
            format: String,
            order: String,
            size: String,
            limit: Int,
            page: Int
        ): Single<CatsModel>
    }
}