package com.brijwel.androidtemplate.bindingadapter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.TooltipCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.brijwel.androidtemplate.widget.SpaceDecoration

class BindingAdapter constructor(
    private val context: Context,
    private val imageLoader: ImageLoader
) {

    @BindingAdapter(
        value = ["app:imageUri", "app:placeholder", "app:placeholderColor"],
        requireAll = false
    )
    fun loadUri(
        imageView: ImageView,
        imageUri: Uri?,
        placeholder: Drawable?,
        @ColorInt placeholderColor: Int?
    ) {
        placeholderColor?.let {
            imageView.background = ColorDrawable(placeholderColor)
        }
        if (imageUri != null) {
            val request = ImageRequest.Builder(imageView.context)
                .data(imageUri)
                .crossfade(1000)
                .placeholder(placeholder)
                .target(imageView)
                .build()
            imageLoader.enqueue(request)
        } else {
            val request = ImageRequest.Builder(imageView.context)
                .data(placeholder)
                .target(imageView)
                .build()
            imageLoader.enqueue(request)
        }
    }

    @BindingAdapter(
        value = ["app:imageUrl", "app:placeholder", "app:placeholderColor"],
        requireAll = false
    )
    fun imageUrl(
        imageView: ImageView,
        imageUrl: String?,
        placeholder: Drawable?,
        @ColorInt placeholderColor: Int?
    ) {
        loadUri(imageView, imageUrl?.toUri(), placeholder, placeholderColor)
    }

    @BindingAdapter("app:text")
    fun setText(textView: TextView, text: String?) {
        text?.let {
            textView.text = text.trim()
        }
    }

    @BindingAdapter("app:text")
    fun setText(view: TextView, @StringRes resId: Int) {
        if (resId == 0) {
            view.text = null
        } else {
            view.setText(resId)
        }
    }

    @BindingAdapter("app:textColor")
    fun seTextColor(text: TextView, @ColorInt color: Int) {
        text.setTextColor(color)
    }

    @BindingAdapter("app:textDrawableTint")
    fun textDrawableTint(textView: TextView, @ColorInt color: Int) {
        textView.compoundDrawables.forEach { drawable ->
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    color,
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    @BindingAdapter("app:underline")
    fun underline(textView: TextView, underline: Boolean) {
        textView.run {
            paintFlags = if (underline) {
                paintFlags or Paint.UNDERLINE_TEXT_FLAG
            } else {
                paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    @BindingAdapter("app:marqueeForever")
    fun marqueeForever(textView: TextView, marquee: Boolean?) {
        marquee?.let {
            if (marquee) {
                textView.ellipsize = TextUtils.TruncateAt.MARQUEE
                textView.isSelected = true
                textView.isSingleLine = true
                textView.isFocusableInTouchMode = true
                textView.marqueeRepeatLimit = -1
            }
        }
    }

    @BindingAdapter("app:tooltip")
    fun toolTip(view: View, tip: String?) {
        TooltipCompat.setTooltipText(view, tip)
    }

    @BindingAdapter("app:itemSpacing")
    fun itemSpacing(view: RecyclerView, @DimenRes id: Int) {
        val space =
            (context.resources.getDimension(id) / context.resources.displayMetrics.density).toInt()
        view.addItemDecoration(SpaceDecoration(space, space, space, space))
    }

    @BindingAdapter("app:itemSpacing")
    fun itemSpacing(view: RecyclerView, dimen: Float) {
        val space = dimen.toInt()
        view.addItemDecoration(SpaceDecoration(space, space, space, space))
    }

    @BindingAdapter("app:pageMargin")
    fun pageMargin(viewPager: ViewPager2, pageMargin: Float) {
        viewPager.setPageTransformer(MarginPageTransformer(pageMargin.toInt()))
    }
}
