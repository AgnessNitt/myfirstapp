package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_dogma).setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.EXTRA_TITLE, "Догма")
            startActivityForResult(intent, 42)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 42) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getBooleanExtra(MainActivity2.RESULT_FAVORITE, false)
                Toast.makeText(this, "Результат $result", Toast.LENGTH_SHORT).show()
            }
        }
    }
}