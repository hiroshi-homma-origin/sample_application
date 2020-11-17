package com.example.timeline.util.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import timber.log.Timber

@GlideModule
class TimeLineGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultTransitionOptions(
            Drawable::class.java,
            DrawableTransitionOptions.withCrossFade()
        )
            .setDefaultTransitionOptions(
                Bitmap::class.java,
                BitmapTransitionOptions.withCrossFade()
            )
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        Timber.d("registerComponents")
        super.registerComponents(context, glide, registry)
    }
}
