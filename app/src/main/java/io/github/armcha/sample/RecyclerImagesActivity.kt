package io.github.armcha.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class RecyclerImagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_images)
        findViewById<RecyclerView>(R.id.recycler).apply {
            adapter = ImageAdapter()
            layoutManager = LinearLayoutManager(this@RecyclerImagesActivity)
        }
    }
}
