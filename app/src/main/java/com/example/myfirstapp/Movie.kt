package com.example.myfirstapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @StringRes
    val title: Int,
    @StringRes
    val description: Int,
    @DrawableRes
    val imageResId: Int,
    val isFavorite: Boolean
)