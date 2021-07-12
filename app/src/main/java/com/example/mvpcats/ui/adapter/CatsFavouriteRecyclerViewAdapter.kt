package com.example.mvpcats.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpcats.R
import com.example.mvpcats.model.database.Cats
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.ui.MainContract

class CatsFavouriteRecyclerViewAdapter(
    private var catsList: List<Cats>,
    private var catsPresenter: MainContract.Presenter,
) : RecyclerView.Adapter<CatsFavouriteRecyclerViewAdapter.CatsViewHolder>() {

    private lateinit var context: Context

    class CatsViewHolder(itemView: View, var catsPresenter: MainContract.Presenter, var catsList: List<Cats>) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.catsImageView)
    }

    fun setImages(cats: List<Cats>) {
        catsList = cats
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cats_item, parent, false)
        return CatsViewHolder(itemView, catsPresenter, catsList)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val imageUrl = catsList[position].url
        Glide.with(context).load(imageUrl).into(holder.imageView)
    }

    override fun getItemCount() = catsList.size
}