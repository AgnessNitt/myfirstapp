package com.example.myfirstapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_dogma).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.EXTRA_TITLE, "Догма")
            intent.putExtra(MainActivity2.EXTRA_DESCRIPTION, getString(R.string.Dogma_text))
            intent.putExtra(MainActivity2.EXTRA_POSTER, R.drawable.dogma)
            startActivityForResult(intent, 42)

        }
        findViewById<Button>(R.id.button_mnenebolno).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.EXTRA_TITLE, "Мне не больно")
            intent.putExtra(MainActivity2.EXTRA_DESCRIPTION, getString(R.string.MNB_text) )
            intent.putExtra(MainActivity2.EXTRA_POSTER, R.drawable.mnenebolno)
            startActivityForResult(intent, 42)
        }
        findViewById<Button>(R.id.bottom_pleasantville).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.EXTRA_TITLE, "Плезантвилль")
            intent.putExtra(MainActivity2.EXTRA_DESCRIPTION, getString(R.string.Pl_text))
            intent.putExtra(MainActivity2.EXTRA_POSTER, R.drawable.pleasantville)
            startActivityForResult(intent, 42)
        }
        findViewById<Button>(R.id.btn_coven).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.EXTRA_TITLE, "Ковен")
            intent.putExtra(MainActivity2.EXTRA_DESCRIPTION, getString(R.string.Coven_text))
            intent.putExtra(MainActivity2.EXTRA_POSTER, R.drawable.coven)
            startActivityForResult(intent, 42)
        }
        findViewById<Button>(R.id.stariki).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.EXTRA_TITLE, "В бой идут одни старики")
            intent.putExtra(MainActivity2.EXTRA_DESCRIPTION, getString(R.string.St_text))
            intent.putExtra(MainActivity2.EXTRA_POSTER, R.drawable.stariki)
            startActivityForResult(intent, 42)
        }
        findViewById<Button>(R.id.truman_show).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.EXTRA_TITLE, "Шоу Трумэна")
            intent.putExtra(MainActivity2.EXTRA_DESCRIPTION, getString(R.string.TS_text))
            intent.putExtra(MainActivity2.EXTRA_POSTER, R.drawable.truman)
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

