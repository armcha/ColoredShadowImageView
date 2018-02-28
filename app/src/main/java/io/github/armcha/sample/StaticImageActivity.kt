package io.github.armcha.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.SeekBar
import io.github.armcha.coloredshadow.ShadowImageView


class StaticImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_image)
        val image = findViewById<ShadowImageView>(R.id.shadowImage).apply {
            radiusOffset = 0.1F
        }
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress == 0)
                    return
                image.radiusOffset = progress / 100f
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        findViewById<Button>(R.id.red).setOnClickListener {
            image.shadowColor = R.color.red
        }
        findViewById<Button>(R.id.blue).setOnClickListener {
            image.shadowColor = R.color.blue
        }
        findViewById<Button>(R.id.green).setOnClickListener {
            image.shadowColor = R.color.green
        }
        findViewById<Button>(R.id.gray).setOnClickListener {
            image.shadowColor = R.color.gray
        }
    }
}
