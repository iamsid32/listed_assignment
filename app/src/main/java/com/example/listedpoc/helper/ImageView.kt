package com.example.listedpoc.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImageWithGif(url: String?) {
    if (url != null) {
        val extension = url.split(".").last()
        if(extension.toLowerCase()=="gif"){
            Glide.with(context)
                .asGif()
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(this)
        }else{
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(this)
        }

    }
}

fun ImageView.loadImageWithGif(url: String?, placeHolder:Int) {
    if (url != null) {
        val extension = url.split(".").last()
        if(extension.toLowerCase()=="gif"){
            Glide.with(context)
                .asGif()
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(this)
        }else{
            Glide.with(context)
                .load(url)
                .placeholder(placeHolder)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(this)
        }

    }
}
fun ImageView.loadImageWithGifNoTransition(url: String?, placeHolder:Int) {
    if (url != null) {
        val extension = url.split(".").last()
        if (extension.toLowerCase() == "gif") {
            Glide.with(context)
                .asGif()
                .load(url)
                .placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(this)
        } else {
            Glide.with(context)
                .load(url)
                .placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(this)
        }

    }
}

fun ImageView.loadImage(url: String?, placeholder:Int) {
    if (url != null) {
        Glide.with(context)
            .load(url)
            .dontAnimate()
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}

fun ImageView.loadImage(url: String?) {
    if (url != null) {
        Glide.with(context)
            .load(url)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}