package com.panic.panicapp.Function

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object NumberFormatter {

    fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault())

        return dateFormat.format(date)
    }
}
