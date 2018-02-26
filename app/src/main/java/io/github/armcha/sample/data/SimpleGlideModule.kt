package io.github.armcha.sample.data

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class SimpleGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context?, builder: GlideBuilder) {
//        val activityManager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20mb
//        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
//        builder.setDefaultRequestOptions(RequestOptions().format(
//                if (ActivityManagerCompat.isLowRamDevice(activityManager)) {
//                    DecodeFormat.PREFER_RGB_565
//                } else {
//                    DecodeFormat.PREFER_ARGB_8888
//                })
//        )
    }
}
