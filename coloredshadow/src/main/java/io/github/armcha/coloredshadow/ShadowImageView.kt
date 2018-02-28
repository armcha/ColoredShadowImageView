package io.github.armcha.coloredshadow

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.FloatRange
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver

class ShadowImageView(context: Context, attributes: AttributeSet? = null) : AppCompatImageView(context, attributes) {

    companion object {
        internal const val DOWNSCALE_FACTOR = 0.25f
        private const val DEFAULT_RADIUS = 0.6f
        private const val DEFAULT_COLOR = -1
        private const val SATURATION = 1.3f
        private const val TOP_OFFSET = 2f
        private const val PADDING = 20f
    }

    @FloatRange(from = 0.0, fromInclusive = false, to = 1.0)
    var radiusOffset = DEFAULT_RADIUS
        set(value) {
            // Log.e("drawable ", "drawable " + drawable)
            //Log.e("drawable ", "drawable " + (field == value))
            if (value <= 0F || value > 1) {
                throw IllegalArgumentException("Radius out of range (0.0 < r <= 1.0)")
            }
            setBlurShadow()
            field = value
        }

    var shadowColor = DEFAULT_COLOR
        set(value) {
            field = value
            setBlurShadow()
        }

    init {
        BlurShadow.init(context.applicationContext)
        cropToPadding = true
        super.setScaleType(ScaleType.CENTER_CROP)
        val padding = dpToPx(PADDING)
        setPadding(padding, padding, padding, padding)
    }

    override fun setImageBitmap(bm: Bitmap?) {
        setBlurShadow { super.setImageDrawable(BitmapDrawable(resources, bm)) }
    }

    override fun setImageResource(resId: Int) {
        setBlurShadow { super.setImageDrawable(ContextCompat.getDrawable(context, resId)) }
    }

    override fun setImageDrawable(drawable: Drawable?) {
        setBlurShadow { super.setImageDrawable(drawable) }
    }

    override fun setScaleType(scaleType: ScaleType?) {
        super.setScaleType(ScaleType.CENTER_CROP)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val specSize = View.MeasureSpec.getSize(heightMeasureSpec)
        if (specMode == View.MeasureSpec.AT_MOST) {
            //TODO
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun setBlurShadow(setImage: () -> Unit = {}) {
        background = null
        if (height != 0 || measuredHeight != 0) {
            setImage()
            makeBlurShadow()
        } else {
            val preDrawListener = object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    viewTreeObserver.removeOnPreDrawListener(this)
                    setImage()
                    makeBlurShadow()
                    //super@ShadowImageView.setImageBitmap(null)
                    return false
                }
            }
            viewTreeObserver.addOnPreDrawListener(preDrawListener)
        }
    }

    private fun makeBlurShadow() {
        var radius = resources.getInteger(R.integer.radius).toFloat()
        radius *= 2 * radiusOffset
        Log.e("radius ", "radius ${hashCode()} " + radius)
        val blur = BlurShadow.blur(this, width, height - dpToPx(TOP_OFFSET), radius)
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(SATURATION)
        background = BitmapDrawable(resources, blur).apply {
            this.colorFilter = ColorMatrixColorFilter(colorMatrix)
            applyShadowColor(this)
        }
    }

    private fun applyShadowColor(bitmapDrawable: BitmapDrawable) {
        if (shadowColor != DEFAULT_COLOR) {
            val color = ContextCompat.getColor(context, shadowColor)
            bitmapDrawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }
}