package com.example.s_denni.googledevkotlin_finalproject_denni_1.tools

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.ubahFormatTanggal

class HelperKtTest {

    @Test
    fun ubahFormatTanggal() {
        val date = "2018-11-24"

        assertEquals("Sab, 24 November 2018", ubahFormatTanggal(date))
    }
}