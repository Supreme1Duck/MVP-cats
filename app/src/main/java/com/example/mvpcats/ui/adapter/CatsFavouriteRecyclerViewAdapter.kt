package com.example.mvpcats.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpcats.R
import com.example.mvpcats.ui.MainContract

class CatsFavouriteRecyclerViewAdapter(
    private var catsList: HashSet<String>,
    private var catsPresenter: MainContract.Presenter,
) : RecyclerView.Adapter<CatsFavouriteRecyclerViewAdapter.CatsViewHolder>() {

    private lateinit var context: Context

    class CatsViewHolder(
        itemView: View
    ) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.catsImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cats_item, parent, false)
        return CatsViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val imageUrl = catsList.elementAt(position)
        Glide.with(context).load(imageUrl).into(holder.imageView)
    }

    override fun getItemCount() = catsList.size
}