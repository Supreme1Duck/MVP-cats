package com.example.mvpcats.presenter

import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.HashSet

@Suppress("UNCHECKED_CAST")
class CatsPresenter<T>(
    activity: MainContract.MarkerView<T>,
    var application: Application
) : MainContract.Presenter {

    private val catsView: MainContract.MarkerView<T> = activity
    private val catsRepository = CatsRepository(application)
    private val disposable = CompositeDisposable()
    private var catsList = CatsModel()
    private var page = 0

    private fun loadCats(): CatsModel {
        disposable.add(
            catsRepository.loadCats(
                format = Constants.FORMAT,
                order = Constants.ORDER_ASC,
                size = Constants.SIZE_FULL,
                limit = Constants.LIMIT_MAX,
                page
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    catsList = it
                    catsView.showCats(catsList as T)
                }
        )
        return catsList
    }

    override fun getCats(): CatsModel {
        page += 1
        return loadCats()
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

    fun mapperCatsToString(hashSet: HashSet<Cats>): HashSet<String> {
        val mappedSet = HashSet<String>()
        hashSet.forEach {
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

    override fun downloadImage(bitmapDrawable: BitmapDrawable) {

//        val contentResolver = application.contentResolver
//        val contentValues = ContentValues()
//        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + ".jpg")
//        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
//        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
//        val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//
//        val outputStream = contentResolver.openOutputStream(imageUri!!)
//        bitmapDrawable.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//        ActivityCompat.requestPermissions(catsView as Activity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

        val bitmap = bitmapDrawable.bitmap

        val filePath = application.getExternalFilesDir(null)
        val dir = File(filePath!!.absolutePath)
        dir.mkdir()
        val file = File(dir, System.currentTimeMillis().toString() + ".jpg")

        val outputStream = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

        Toast.makeText(application, "Downloaded!", Toast.LENGTH_SHORT).show()

        outputStream.flush()
        outputStream.close()
    }

    override fun onDestroy() {
        disposable.clear()
    }
}