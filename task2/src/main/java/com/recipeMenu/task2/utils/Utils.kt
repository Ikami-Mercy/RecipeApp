package com.recipeMenu.task2.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {


    @SuppressLint("SimpleDateFormat")
    fun getToDate(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMM")
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat("dd MMM")
            formatter.format(date)
        }
    }
}