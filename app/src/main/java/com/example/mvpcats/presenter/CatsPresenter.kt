package com.example.mvpcats.presenter

import android.util.Log
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.ui.ScrollingActivity
import com.example.mvpcats.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatsPresenter(
    scrollingActivity: ScrollingActivity
) : MainContract.Presenter {

    private val catsView: MainContract.View = scrollingActivity
    private val catsRepository = CatsRepository()
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
                    Log.d("WEB_CALL", catsList[20].url)
                    catsView.showCats(catsList)
                }
        )
        return catsList
    }

    override fun onDestroy() {
        disposable.clear()
    }
}