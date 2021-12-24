package com.dicoding.caritahu.helper

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TextViewHelper {
    fun referenceStyling(references: String): SpannableString {
        val refSpan = SpannableString(references)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            refSpan.setSpan(BulletSpan(), 10, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return refSpan
    }

    fun convertToHtml(paragraph: String): Spanned {
        return Html.fromHtml(paragraph, Html.FROM_HTML_MODE_COMPACT)
    }

    fun hoaxDetailDate(currentFormat: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dateParse = LocalDate.parse(currentFormat, dateFormat)

            "${dateParse.dayOfWeek}, ${dateParse.dayOfMonth} ${dateParse.month} ${dateParse.year}"
        } else {
            currentFormat
        }
    }

    fun newsDetailDate(currentFormat: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val dateParse = LocalDate.parse(currentFormat, formatter)

            "${dateParse.dayOfWeek}, ${dateParse.dayOfMonth} ${dateParse.month} ${dateParse.year}"
        } else {
            currentFormat
        }
    }
}