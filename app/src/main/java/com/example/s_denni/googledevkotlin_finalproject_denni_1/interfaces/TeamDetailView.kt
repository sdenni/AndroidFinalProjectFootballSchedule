package com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces

import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.KlubSepakBola

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<KlubSepakBola>)
}