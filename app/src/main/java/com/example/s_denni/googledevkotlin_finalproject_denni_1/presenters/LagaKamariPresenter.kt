package com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters

import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.MyLagaView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfMyLaga
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfMySearchedLaga
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.TheSportDBApi
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LagaKamariPresenter (private val view: MyLagaView,
                           private val apiRepository: DataRepository,
                           private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

        fun nimmLagaKamari(leagueStr: String?, isById: Boolean) {
            view.showLoading()

            GlobalScope.launch (context.main){

                if(isById.let { it == true }){
                    val data = gson.fromJson(apiRepository
                        .machenRequest(TheSportDBApi.nimmLastMatch(leagueStr)).await(),
                        ListOfMyLaga::class.java)
                    view.showEventList(data.events)
                } else if (isById.let { it == false }) {
                    val data = gson.fromJson(apiRepository
                        .machenRequest(TheSportDBApi.nimmMatchByLeagueName(leagueStr)).await(),
                        ListOfMySearchedLaga::class.java)
                    view.showEventList(data.event)
                }
                view.hideLoading()
            }
        }

}