package com.brijwel.androidtemplate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brijwel.androidtemplate.R
import com.brijwel.androidtemplate.data.pref.PreferenceStorage
import com.brijwel.androidtemplate.databinding.ActivityMainBinding
import com.brijwel.androidtemplate.utils.CodeSnippet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var codeSnippet: CodeSnippet

    @Inject
    lateinit var preferenceStorage: PreferenceStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }
}
