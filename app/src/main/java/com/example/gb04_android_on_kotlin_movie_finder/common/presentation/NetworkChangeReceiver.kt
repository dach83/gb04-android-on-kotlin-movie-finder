package com.example.gb04_android_on_kotlin_movie_finder.common.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class NetworkChangeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "NetworkChangeReceiver", Toast.LENGTH_SHORT).show()
    }
}