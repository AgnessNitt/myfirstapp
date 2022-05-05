package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)




        title = intent.getStringExtra(EXTRA_TITLE) ?: error("No title provided")

        val result = Intent()
        result.putExtra(RESULT_FAVORITE, true)
        setResult(RESULT_OK, result)
    }

    companion object {
        const val EXTRA_TITLE = "title"
        const val RESULT_FAVORITE = "favorite"
    }
}