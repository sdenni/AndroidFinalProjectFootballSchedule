package com.example.s_denni.googledevkotlin_finalproject_denni_1.tools

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import org.jetbrains.anko.db.NULL
import java.sql.Time
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

fun toGMTFormat(date: String, time: String): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}

fun fromGMTtoDate(string_gmt: String): String?{
    val format = SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.getDefault())
    val date: Date = format.parse(string_gmt)

    val pattern = "EEE, dd MMM yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())

    return simpleDateFormat.format(date)
}

fun fromGMTtoTime(string_gmt: String): String?{
    val format = SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.getDefault())
    val date: Date = format.parse(string_gmt)

    val pattern = "HH:mm"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())

    return simpleDateFormat.format(date)
}