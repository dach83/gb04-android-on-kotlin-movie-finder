package com.example.gb04_android_on_kotlin_movie_finder.common.domain

import android.os.Build
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.util.stream.Collectors

fun View.showSnackBar(msg: String, duration: Int = Snackbar.LENGTH_INDEFINITE) =
    Snackbar
        .make(this, msg, duration)
        .show()


fun View.showSnackBar(msgId: Int, duration: Int = Snackbar.LENGTH_INDEFINITE) =
    showSnackBar(context.getText(msgId).toString(), duration)


fun BufferedReader.getLines(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        lines().collect(Collectors.joining("\n"))
    } else {
        TODO("VERSION.SDK_INT < N")
    }
}