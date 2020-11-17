package com.example.timeline.util.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

@BindingAdapter("imageFromUrl", "placeholder", requireAll = false)
fun bindImageFromUrlWithPlaceholder(
    view: ImageView,
    imageFromUrl: String?,
    placeholder: Drawable?
) {
    GlideApp.with(view.context)
        .load(imageFromUrl)
        .placeholder(placeholder)
        .into(view)
}
