package com.example.gb04_android_on_kotlin_movie_finder.presentation.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.gb04_android_on_kotlin_movie_finder.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}