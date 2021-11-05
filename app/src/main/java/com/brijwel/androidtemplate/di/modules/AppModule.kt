package com.brijwel.androidtemplate.di.modules

import android.content.Context
import coil.ImageLoader
import com.brijwel.androidtemplate.App
import com.brijwel.androidtemplate.bindingadapter.BindingComponent
import com.brijwel.androidtemplate.utils.CodeSnippet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideApp(@ApplicationContext context: Context): App = context as App

    @Singleton
    @Provides
    fun provideCodeSnippet(application: App): CodeSnippet = CodeSnippet(application)

    @Singleton
    @Provides
    fun provideBindingComponent(
        @ApplicationContext context: Context,
        imageLoader: ImageLoader
    ): BindingComponent = BindingComponent(context, imageLoader)
}
