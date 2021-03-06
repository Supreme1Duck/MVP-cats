package com.example.mvpcats.ui

 import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpcats.CatsApplication
import com.example.mvpcats.R
import com.example.mvpcats.databinding.ActivityScrollingBinding
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.ui.adapter.CatsRecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ScrollingActivity : AppCompatActivity(), MainContract.CatsView {

    private lateinit var binding: ActivityScrollingBinding
    private val cats = CatsModel()
    @Inject
    lateinit var catsPresenter : CatsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        CatsApplication.applicationComponent.inject(this)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.recyclerView.catsRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener {
            val intent = Intent(this, FavouriteCatsActivity::class.java)
            startActivity(intent)
        }
        binding.fab1.setOnClickListener {
            catsPresenter.getCats()
            Snackbar.make(it, "New pictures added!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        binding.recyclerView.catsRecyclerView.adapter = CatsRecyclerViewAdapter(cats, catsPresenter)
        catsPresenter.getCats()
    }

    override fun onDestroy() {
        super.onDestroy()
        catsPresenter.onDestroy()
    }

    override fun showCats(t: CatsModel) {
        cats.addAll(t)
        binding.recyclerView.catsRecyclerView.adapter!!.notifyDataSetChanged()
    }
}