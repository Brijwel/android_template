package com.brijwel.androidtemplate

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.brijwel.androidtemplate.bindingadapter.BindingComponent
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var bindingComponent: BindingComponent

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(bindingComponent)
    }
}
