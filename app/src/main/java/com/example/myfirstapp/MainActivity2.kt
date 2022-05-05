package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)




        title = intent.getStringExtra(EXTRA_TITLE) ?: error("No title provided")

        val text = intent.getStringExtra(TEXT_VIEW) ?: error("No title provided")

        val dicription = findViewById<TextView>(R.id.button_dogma)
        dicription.text = text


        val result = Intent()
        result.putExtra(RESULT_FAVORITE, true)
        setResult(RESULT_OK, result)


    }


    companion object {
        const val EXTRA_TITLE = "title"
        const val RESULT_FAVORITE = "favorite"
        const val TEXT_VIEW = "Text"
    }
}