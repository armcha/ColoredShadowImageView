package io.github.armcha.coloredshadow

import android.util.TypedValue
import android.view.View


fun View.dpToPx(dp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
}