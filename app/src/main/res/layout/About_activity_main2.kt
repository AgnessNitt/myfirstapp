package layout

import android.app.Activity
import android.os.Bundle

class AboutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.button_dogma)
    }
}