package com.util.bindingAdapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R


@BindingAdapter(value = ["setImage", "imageSource", "placeholder"], requireAll = false)
fun setImage(
    imageView: ImageView, image: Int?, imageSource: String?, placeholder: Drawable?
) {
    if (image != null) {
        Glide.with(imageView.context).load(ContextCompat.getDrawable(imageView.context, image))
            .circleCrop()
            .error(
                placeholder ?: ContextCompat.getDrawable(
                    imageView.context, R.drawable.android_icon
                )
            ).into(imageView)
    } else {
        Glide.with(imageView.context).load(imageSource)
            .circleCrop()
            .error(
                placeholder ?: ContextCompat.getDrawable(
                    imageView.context, R.drawable.android_icon
                )
            ).into(imageView)
    }


}





