package com.example.gb04_android_on_kotlin_movie_finder.domain

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(msg: String, duration: Int = Snackbar.LENGTH_INDEFINITE) =
    Snackbar
        .make(this, msg, duration)
        .show()


fun View.showSnackBar(msgId: Int, duration: Int = Snackbar.LENGTH_INDEFINITE) =
    showSnackBar(context.getText(msgId).toString(), duration)
