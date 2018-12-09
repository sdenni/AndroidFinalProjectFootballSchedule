package com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces

import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.KlubSepakBolaResponse
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfMyLaga

interface LagaDetailView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(
        myLagaInfo: ListOfMyLaga,
        kencaKlubData: KlubSepakBolaResponse,
        katuhuKlubData: KlubSepakBolaResponse
    )
}