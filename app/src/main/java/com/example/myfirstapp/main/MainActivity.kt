package com.example.myfirstapp.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() = showExitDialog()

    private fun showExitDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.exit_dialog_message)
            .setTitle(R.string.exit_dialog_title)
            .setPositiveButton(R.string.button_yes) { dialog, id ->
                dialog.dismiss()
                this.finish()
            }
            .setNegativeButton(R.string.button_no) { dialog, id ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

