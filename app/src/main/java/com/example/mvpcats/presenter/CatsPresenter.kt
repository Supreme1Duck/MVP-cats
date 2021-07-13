package com.example.mvpcats.presenter

import android.app.Application
import android.util.Log
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@Suppress("UNCHECKED_CAST")
class CatsPresenter<T>(
    activity: MainContract.MarkerView<T>,
    application: Application
) : MainContract.Presenter {

    private val catsView: MainContract.MarkerView<T> = activity
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
                    catsView.showCats(catsList as T)
                }
        )
        return catsList
    }

    override fun getFavouriteCats(): Set<Cats> {
        var cats = HashSet<Cats>()
        disposable.add(
            catsRepository.getFavouriteCats()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    cats = HashSet(it)
                    catsView.showCats(mapperCatsToString(cats) as T)
                }
        )
        return cats
    }

    fun mapperCatsToString(hashSet: HashSet<Cats>): HashSet<String>{
        val mappedSet = HashSet<String>()
        hashSet.forEach{
            mappedSet.add(it.url)
        }
        return mappedSet
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