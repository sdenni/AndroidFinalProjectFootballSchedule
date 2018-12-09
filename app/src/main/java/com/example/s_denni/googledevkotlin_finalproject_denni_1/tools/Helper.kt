package com.example.s_denni.googledevkotlin_finalproject_denni_1.tools

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

@SuppressLint("SimpleDateFormat")
fun ubahFormatTanggal(date_string : String): String{

    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    val date: Date = format.parse(date_string)

    val pattern = "EEE, dd MMMM yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern)

    return simpleDateFormat.format(date)

}

fun ubahFormatWaktu(waktu_string : String) :String {
    val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    val date: Date = format.parse(waktu_string)

    val pattern = "HH:mm"
    val simpleDateFormat = SimpleDateFormat(pattern)

    return simpleDateFormat.format(date)
}