package com.example.myfirstapp

import androidx.annotation.DrawableRes

data class Movie(
    val title: String,
    val description: String,
    @DrawableRes
    val imageResId: Int,
)