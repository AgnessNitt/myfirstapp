package com.example.myfirstapp.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.movie.MovieActivity
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource
import com.example.myfirstapp.favorite.FavoriteMoviesActivity
import com.example.myfirstapp.observer.MovieObserver


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getBooleanExtra(MovieActivity.KEY_RESULT_FAVORITE, false)
                Toast.makeText(
                    this,
                    getString(R.string.result_pattern, result.toString()),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            showExitDialog()
        } else {
            super.onBackPressed()
        }
    }

    private fun showExitDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.exit_dialog_message)
            .setTitle(R.string.exit_dialog_title)
            .setPositiveButton(R.string.button_yes) { dialog, id ->
                this.finish()
            }
            .setNegativeButton(R.string.button_no) { dialog, id ->
                dialog.cancel()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}