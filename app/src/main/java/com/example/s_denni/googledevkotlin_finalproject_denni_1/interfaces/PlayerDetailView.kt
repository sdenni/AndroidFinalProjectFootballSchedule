package com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces

import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfPlayers

interface PlayerDetailView {
        fun showLoading()
        fun hideLoading()
        fun showEventList(
            playerData: ListOfPlayers
        )
}