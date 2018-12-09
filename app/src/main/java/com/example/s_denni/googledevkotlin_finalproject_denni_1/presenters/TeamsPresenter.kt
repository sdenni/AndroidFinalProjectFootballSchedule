package com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters

import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.TeamsView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.KlubSepakBolaResponse
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.TheSportDBApi
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: DataRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(strSearch: String?, isLeague: Boolean?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            if(isLeague.let { it == true }){
                val data = gson.fromJson(apiRepository
                    .machenRequest(TheSportDBApi.getTeams(strSearch)).await(),
                    KlubSepakBolaResponse::class.java)
                view.showTeamList(data.teams)
            } else if (isLeague.let { it == false }) {
                val data = gson.fromJson(apiRepository
                    .machenRequest(TheSportDBApi.getTeamsByName(strSearch)).await(),
                    KlubSepakBolaResponse::class.java)
                view.showTeamList(data.teams)
            }
            view.hideLoading()
        }
    }
}