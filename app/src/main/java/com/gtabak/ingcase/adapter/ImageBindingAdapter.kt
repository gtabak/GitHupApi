package com.gtabak.ingcase.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import android.app.Dialog
import com.gtabak.ingcase.R


object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {

        if (url != null) {

            val dialog = Dialog(view.context)
            dialog.show()

            Glide.with(view.context).load(url).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageResource(com.gtabak.ingcase.R.drawable.ic_person_outline_black_24dp)
                    dialog.cancel()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    dialog.cancel()
                    return false
                }
            }).into(view)

        } else {

            view.setImageResource(R.drawable.ic_person_outline_black_24dp)


        }
    }
}
