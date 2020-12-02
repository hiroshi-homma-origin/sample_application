package com.example.timeline.util.ext

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.timeline.R
import com.kotlin.project.data.model.TimeLineStatus

@BindingAdapter("visibleGone")
fun visibleGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.isRefresh(timeLineStatus: TimeLineStatus?) {
    timeLineStatus ?: return
    isRefreshing = timeLineStatus == TimeLineStatus.RELOADING
}

@BindingAdapter("imageFromUrl", requireAll = false)
fun bindImageFromUrlWithPlaceholder(
    view: ImageView,
    imageFromUrl: String?
) {
    GlideApp.with(view.context)
        .load(imageFromUrl)
        .downsample(DownsampleStrategy.CENTER_INSIDE)
        .dontTransform()
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                p0: GlideException?,
                p1: Any?,
                target: Target<Drawable>?,
                p3: Boolean
            ): Boolean {
                // NOP
                return false
            }

            override fun onResourceReady(
                p0: Drawable?,
                p1: Any?,
                target: Target<Drawable>?,
                p3: DataSource?,
                p4: Boolean
            ): Boolean {
                // do something when picture already loaded
                return false
            }
        })
        .placeholder(R.color.gray1)
        .fallback(R.color.gray1)
        .override(400, 400)
        .into(view)
}

@BindingAdapter("widthDp", "heightDp")
fun setHeightDp(view: View, widthDp: Int?, heightDp: Int?) {
    val width = widthDp?.toPx() ?: return
    val height = heightDp?.toPx() ?: return
    val layoutParams = view.layoutParams
    layoutParams.width = width
    layoutParams.height = height
    view.layoutParams = layoutParams
}

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
