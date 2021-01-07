package com.example.testmandiri

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imgMovieBackdrop")
fun bindImageMovieBackdrop(view: ImageView, imageUrl: String?) {
    val backdropPath = "https://image.tmdb.org/t/p/w500"
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(backdropPath + imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("imgMoviePoster")
fun bindImageMoviePoster(view: ImageView, imageUrl: String?) {
    val posterPath = "https://image.tmdb.org/t/p/w500"
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(posterPath + imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}