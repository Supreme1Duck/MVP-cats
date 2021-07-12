package com.example.mvpcats.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpcats.databinding.FavouriteCatsActivityBinding
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.ui.adapter.CatsFavouriteRecyclerViewAdapter

class FavouriteCatsCatsActivity : AppCompatActivity(), MainContract.FavouriteCatsView {

    private lateinit var catsPresenter: MainContract.Presenter
    private lateinit var catsList: ArrayList<Cats>

    private val binding by lazy {
        FavouriteCatsActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
        catsPresenter = CatsPresenter(this, application)
        binding.favouritesRecyclerView.layoutManager = GridLayoutManager(this, 3)
        Log.d("WOW", "WOW")
    }

    override fun showCats(cats: List<Cats>) {
        binding.favouritesRecyclerView.adapter =
            CatsFavouriteRecyclerViewAdapter(cats, catsPresenter)
    }
}