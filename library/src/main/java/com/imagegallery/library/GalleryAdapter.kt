package com.imagegallery.library

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

/**
 * Created by Josh on 01,Dec,2018
 */
internal class GalleryAdapter(
    private val context: Context,
    private val data: List<String>
) : RecyclerView.Adapter<GalleryAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            PhotoView(context).apply {
                layoutParams = RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT
                )
            }
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String?) = with(itemView) {
            Glide.with(this)
                .load(item)
                .into(this as ImageView)
            setOnClickListener { (context as ImageTapInterface).onTap() }
        }
    }

    interface ImageTapInterface {
        fun onTap()
    }
}