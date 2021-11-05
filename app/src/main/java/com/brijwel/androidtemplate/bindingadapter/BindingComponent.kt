package com.brijwel.androidtemplate.bindingadapter

import android.content.Context
import androidx.databinding.DataBindingComponent
import coil.ImageLoader
import javax.inject.Inject

class BindingComponent @Inject constructor(
    private val context: Context,
    private val imageLoader: ImageLoader
) : DataBindingComponent {
    override fun getBindingAdapter(): BindingAdapter = BindingAdapter(context, imageLoader)
}
