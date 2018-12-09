package com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters

import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.TheSportDBApi
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.LagaDetailView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.KlubSepakBolaResponse
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfMyLaga
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailKlubPresenter(
    private val view: LagaDetailView,
    private val apiRepository: DataRepository,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun nimmKlubDetail(id_schedule: String?){
        view.showLoading()

        GlobalScope.launch (context.main) {

            val dataSchedule = gson.fromJson(apiRepository
                .machenRequest(TheSportDBApi.nimmMatch(id_schedule)).await(),
                ListOfMyLaga::class.java
            )

            val dataKenca = gson.fromJson(apiRepository
                .machenRequest(TheSportDBApi.nimmKlubInfo(dataSchedule.events[0].idKlubKenca)).await(),
                KlubSepakBolaResponse::class.java
            )

            val dataKatuhu = gson.fromJson(apiRepository
                .machenRequest(TheSportDBApi.nimmKlubInfo(dataSchedule.events[0].idKlubKatuhu)).await(),
                KlubSepakBolaResponse::class.java
            )

            view.hideLoading()
            view.showEventList(dataSchedule, dataKenca, dataKatuhu)
        }
    }

}