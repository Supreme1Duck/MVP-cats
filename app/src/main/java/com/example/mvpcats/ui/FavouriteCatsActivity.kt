package com.example.mvpcats.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpcats.databinding.FavouriteCatsActivityBinding
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.database.CatsDatabase
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.presenter.FavouriteCatsPresenter
import com.example.mvpcats.ui.adapter.CatsFavouriteRecyclerViewAdapter
import javax.inject.Inject

class FavouriteCatsActivity : AppCompatActivity(), MainContract.FavouriteCatsView {

    @Inject
    lateinit var catsPresenter: FavouriteCatsPresenter
    private lateinit var binding: FavouriteCatsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavouriteCatsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        catsPresenter.getFavouriteCats()
        binding.favouritesRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    override fun showCats(t: HashSet<String>) {
        binding.favouritesRecyclerView.adapter =
            CatsFavouriteRecyclerViewAdapter(t, catsPresenter)
    }
}