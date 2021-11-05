package com.brijwel.androidtemplate.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class LauncherActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_SCREEN_DURATION = 1000L
    }

    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepVisibleCondition { !isReady }
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val iconView = splashScreenViewProvider.iconView
            iconView.animate()
                .setDuration(500L)
                .alpha(0f)
                .withEndAction {
                    startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
                    finish()
                    splashScreenViewProvider.remove()
                }.start()
        }
        Handler(Looper.getMainLooper()).postDelayed({ isReady = true }, SPLASH_SCREEN_DURATION)
    }
}
