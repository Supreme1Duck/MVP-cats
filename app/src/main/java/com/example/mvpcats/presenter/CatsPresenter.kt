package com.example.mvpcats.presenter

import android.app.Application
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatsPresenter(
    scrollingActivity: MainContract.View,
    application: Application
) : MainContract.Presenter {

    private val catsView: MainContract.View = scrollingActivity
    private val catsRepository = CatsRepository(application)
    private val disposable = CompositeDisposable()
    private var catsList = CatsModel()

    override fun onActivityCreated(): CatsModel {
        disposable.add(
            catsRepository.loadCats(
                format = Constants.FORMAT,
                order = Constants.ORDER_ASC,
                size = Constants.SIZE_FULL,
                limit = Constants.LIMIT_MAX,
                page = Constants.PAGE_MIN
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    catsList = it
                    catsView.showCats(catsList)
                }
        )
        return catsList
    }

    override fun getFavouriteCats(): ArrayList<Cats> {
        var cats = ArrayList<Cats>()
        disposable.add(
            catsRepository.getFavouriteCats()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    cats = it as ArrayList<Cats>
                }
        )
        return cats
    }

    override fun insertCat(cat: Cats) {
        disposable.add(
            catsRepository.insertFavouriteCat(cat)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {}
        )
    }

    override fun onDestroy() {
        disposable.clear()
    }
}