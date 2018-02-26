package io.github.armcha.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.staticImageButton).setOnClickListener {
            startActivity(Intent(this@MainActivity, StaticImageActivity::class.java))
        }
        findViewById<Button>(R.id.recyclerViewImageButton).setOnClickListener {
            startActivity(Intent(this@MainActivity, RecyclerImagesActivity::class.java))
        }
    }
}
