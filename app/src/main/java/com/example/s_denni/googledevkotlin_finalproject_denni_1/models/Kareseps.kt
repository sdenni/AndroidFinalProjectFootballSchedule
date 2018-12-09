package com.example.s_denni.googledevkotlin_finalproject_denni_1.models

data class Kareseps (
    val kode: Long?,
    val scheduleKode: String?,
    val kencaKode: String?,
    val katuhuKode: String?,
    val kencaName: String?,
    val katuhuName:String?,
    val kencaScore: String?,
    val katuhuScore: String?,
    val tanggalLiga: String?
){

        companion object {
            const val TABLE_KARESEP: String = "TABLE_KARESEP"
            const val ID: String = "ID_"
            const val ID_SCHEDULE = "ID_SCHEDULE"
            const val ID_KENCA = "ID_KENCA"
            const val ID_KATUHU = "ID_KATUHU"
            const val KENCA_NAME = "KENCA_NAME"
            const val KATUHU_NAME = "KATUHU_NAME"
            const val KENCA_SCORE = "KENCA_SCORE"
            const val KATUHU_SCORE = "KATUHU_SCORE"
            const val TANGGAL_LIGA = "TANGGAL_LIGA"
        }
}