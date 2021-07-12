package com.example.mvpcats.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpcats.R
import com.example.mvpcats.databinding.FavouriteCatsActivityBinding
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.ui.adapter.CatsRecyclerViewAdapter

class FavouriteCatsActivity : AppCompatActivity(), MainContract.View {

    private lateinit var catsPresenter: MainContract.Presenter

    private val binding by lazy {
        FavouriteCatsActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
        catsPresenter = CatsPresenter(this, application)
        binding.favouritesRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    override fun showCats(cats: CatsModel) {
        binding.favouritesRecyclerView.adapter = CatsRecyclerViewAdapter(cats, catsPresenter)
    }
}