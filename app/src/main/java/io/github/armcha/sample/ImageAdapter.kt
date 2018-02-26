package io.github.armcha.sample

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import io.github.armcha.coloredshadow.ShadowImageView
import io.github.armcha.sample.data.DataSource
import io.github.armcha.sample.data.GlideApp
import io.github.armcha.sample.data.Item


class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val items by lazy {
        DataSource.items
    }

    override fun getItemViewType(position: Int) = R.layout.items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val shadowView by lazy { itemView.findViewById<ShadowImageView>(R.id.shadowView) }
        private val text by lazy { itemView.findViewById<TextView>(R.id.text) }

        fun bind(item: Item) {
            text.text = item.name
            GlideApp.with(itemView.context)
                    .asBitmap()
                    .load(item.imageUrl)
                    .transform(CircleCrop())
                    //.transform(RoundedCorners(206))
                    .into(shadowView)
        }
    }
}
