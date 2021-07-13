package com.example.mvpcats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpcats.databinding.FavouriteCatsActivityBinding
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.ui.adapter.CatsFavouriteRecyclerViewAdapter

class FavouriteCatsActivity : AppCompatActivity(), MainContract.FavouriteCatsView {

    private lateinit var catsPresenter: MainContract.Presenter
    private lateinit var binding: FavouriteCatsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavouriteCatsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        catsPresenter = CatsPresenter(this, application)
        catsPresenter.getFavouriteCats()
        binding.favouritesRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    override fun showCats(t: HashSet<String>) {
        binding.favouritesRecyclerView.adapter =
            CatsFavouriteRecyclerViewAdapter(t, catsPresenter)
    }
}