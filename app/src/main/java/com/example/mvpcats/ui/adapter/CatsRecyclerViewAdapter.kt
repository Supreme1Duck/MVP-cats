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
import com.bumptech.glide.Priority
import com.example.mvpcats.R
import com.example.mvpcats.model.entity.CatsModel

class CatsRecyclerViewAdapter(
    private val catsList: CatsModel,
    private val onImageClickListener: OnImageClickListener
) : RecyclerView.Adapter<CatsRecyclerViewAdapter.CatsViewHolder>() {

    private lateinit var context: Context

    class CatsViewHolder(itemView: View, onClickListener: OnImageClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imageView: ImageView = itemView.findViewById(R.id.catsImageView)
        var onClickListener = onClickListener

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onClickListener.onImageClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cats_item, parent, false)
        return CatsViewHolder(itemView, onImageClickListener)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val imageUrl = catsList[position].url
        Glide.with(context).load(imageUrl).into(holder.imageView)
    }

    override fun getItemCount() = catsList.size

    interface OnImageClickListener {
        fun onImageClick(position: Int) : String
    }

}