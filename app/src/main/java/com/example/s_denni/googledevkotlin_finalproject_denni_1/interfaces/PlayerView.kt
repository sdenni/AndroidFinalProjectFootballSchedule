package com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces

import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.Player

interface PlayerView {
        fun showLoading()
        fun hideLoading()
        fun showEventList(myPlayer: List<Player>)
}