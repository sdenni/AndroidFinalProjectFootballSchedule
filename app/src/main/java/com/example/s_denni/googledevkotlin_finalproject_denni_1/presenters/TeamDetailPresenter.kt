package com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters

import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.TeamDetailView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.KlubSepakBolaResponse
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.TheSportDBApi
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by root on 2/3/18.
 */
class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: DataRepository,
                          private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                        .machenRequest(TheSportDBApi.nimmKlubInfo(teamId)).await(),
                        KlubSepakBolaResponse::class.java)

            view.showTeamDetail(data.teams)
            view.hideLoading()
        }
    }
}