package com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters

import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.PlayerView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfPlayer
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.TheSportDBApi
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter (private val view: PlayerView,
                           private val apiRepository: DataRepository,
                           private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun nimmPlayer(klubName: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .machenRequest(TheSportDBApi.nimmPlayerKlub(klubName)).await(),
                ListOfPlayer::class.java)

            view.showEventList(data.player)
            view.hideLoading()
        }
    }
}