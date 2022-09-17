package com.example.myfirstapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    val title: String,
    val description: String,
    @DrawableRes
    val imageResId: Int,
    val isFavorite: Boolean
)