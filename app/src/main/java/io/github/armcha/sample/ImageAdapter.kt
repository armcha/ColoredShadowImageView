package io.github.armcha.sample

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import io.github.armcha.coloredshadow.ShadowImageView
import io.github.armcha.sample.data.DataSource
import io.github.armcha.sample.data.GlideApp
import io.github.armcha.sample.data.Item


class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val items by lazy {
        DataSource.items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
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
            shadowView.radiusOffset = 0.7f
            //shadowView.shadowColor = Color.GRAY
            GlideApp.with(itemView.context)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.place_holder)
                    //.transform(CircleCrop())
                    .into(object : ViewTarget<ImageView, Drawable>(shadowView) {
                        override fun onLoadStarted(placeholder: Drawable?) {
                            super.onLoadStarted(placeholder)
                            shadowView.setImageDrawable(placeholder, withShadow = false)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            super.onLoadCleared(placeholder)
                            shadowView.setImageDrawable(placeholder, withShadow = false)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            shadowView.setImageDrawable(errorDrawable, withShadow = false)
                        }

                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            shadowView.setImageDrawable(resource)
                        }
                    })
        }
    }
}
