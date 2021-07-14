package com.example.mvpcats.presenter

import com.example.mvpcats.CatsApplication
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.ui.FavouriteCatsActivity
import com.example.mvpcats.ui.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavouriteCatsPresenter : MainContract.FavouriteCatsPresenter{

    init {
        CatsApplication.applicationComponent.inject(this)
    }

    @Inject
    lateinit var catsRepository : CatsRepository
    @Inject
    lateinit var catsView: FavouriteCatsActivity
    private val disposable = CompositeDisposable()

    override fun getFavouriteCats(): Set<Cats> {
        var cats = HashSet<Cats>()
        disposable.add(
            catsRepository.getFavouriteCats()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    cats = HashSet(it)
                    catsView.showCats(mapperCatsToString(cats))
                }
        )
        return cats
    }

    override fun mapperCatsToString(hashSet: HashSet<Cats>): HashSet<String> {
        val mappedSet = HashSet<String>()
        hashSet.forEach {
            mappedSet.add(it.url)
        }
        return mappedSet
    }

    override fun onDestroy() {
        disposable.clear()
    }

}