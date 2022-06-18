package com.example.myfirstapp.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.R


class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)




        title = intent.getStringExtra(EXTRA_TITLE) ?: error("No title provided")

        val text = intent.getStringExtra(EXTRA_DESCRIPTION) ?: error("No title provided")

        val description = findViewById<TextView>(R.id.description)
        description.text = text

        val posterId = intent.getIntExtra(EXTRA_POSTER, -1)
        findViewById<ImageView>(R.id.poster_d).setImageResource(posterId)

        val result = Intent()
        result.putExtra(RESULT_FAVORITE, true)
        setResult(RESULT_OK, result)


    }


    companion object {
        const val EXTRA_TITLE = "title"
        const val RESULT_FAVORITE = "favorite"
        const val EXTRA_DESCRIPTION = "description"
        const val EXTRA_POSTER = "poster"
    }
}