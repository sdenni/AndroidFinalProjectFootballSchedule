package com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters

import android.util.Log
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.PlayerDetailView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfPlayers
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.TheSportDBApi
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPlayerPresenter(
    private val view: PlayerDetailView,
    private val apiRepository: DataRepository,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun nimmDetailPlayer(id_player: String?){
        view.showLoading()

        GlobalScope.launch (context.main) {

            Log.d("TRACE","URL "+TheSportDBApi.nimmPlayer(id_player))

            val data = gson.fromJson(apiRepository
                .machenRequest(TheSportDBApi.nimmPlayer(id_player)).await(),
                ListOfPlayers::class.java
            )

            view.hideLoading()
            view.showEventList(data)
        }
    }

}