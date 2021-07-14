package com.example.mvpcats.presenter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mvpcats.CatsApplication
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.ui.ScrollingActivity
import com.example.mvpcats.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CatsPresenter : MainContract.Presenter {

    init {
        CatsApplication.applicationComponent.inject(this)
    }

    @Inject
    lateinit var catsView: ScrollingActivity
    @Inject
    lateinit var repository: CatsRepository
    private val disposable = CompositeDisposable()
    private var catsList = CatsModel()
    private var page = 0

    private fun loadCats(): CatsModel {
        disposable.add(
            repository.loadCats(
                format = Constants.FORMAT,
                order = Constants.ORDER_ASC,
                size = Constants.SIZE_FULL,
                limit = Constants.LIMIT_MAX,
                page
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    catsList = it
                    catsView.showCats(catsList)
                }
        )
        return catsList
    }

    override fun getCats(): CatsModel {
        page += 1
        return loadCats()
    }

    override fun insertCat(cat: Cats) {
        disposable.add(
            repository.insertFavouriteCat(cat)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {}
        )
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun downloadImage(bitmapDrawable: BitmapDrawable) {
        val bitmap = bitmapDrawable.bitmap

        val filePath = catsView.application.getExternalFilesDir(null)
        val dir = File(filePath!!.absolutePath + "/downloads")
        dir.mkdir()
        val file = File(dir, System.currentTimeMillis().toString() + ".jpg")

        val outputStream = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

        Toast.makeText(catsView, "Downloaded!", Toast.LENGTH_SHORT).show()

        outputStream.flush()
        outputStream.close()
    }

    override fun onDestroy() {
        disposable.clear()
    }
}