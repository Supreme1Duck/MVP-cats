package com.example.mvpcats.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpcats.R
import com.example.mvpcats.databinding.ActivityScrollingBinding
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.ui.adapter.CatsRecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar

class ScrollingActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var cats: CatsModel
    private val catsPresenter by lazy {
        CatsPresenter(this, application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cats = CatsModel()
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.recyclerView.catsRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        catsPresenter.onActivityCreated()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        catsPresenter.onDestroy()
    }

    override fun showCats(cats: CatsModel) {
        binding.recyclerView.catsRecyclerView.adapter = CatsRecyclerViewAdapter(cats, catsPresenter)
    }
}