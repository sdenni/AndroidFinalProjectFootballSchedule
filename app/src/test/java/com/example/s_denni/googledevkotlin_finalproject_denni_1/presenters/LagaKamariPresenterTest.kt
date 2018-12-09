package com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters

import com.example.s_denni.googledevkotlin_finalproject_denni_1.TestContextProvider
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.MyLagaView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfMyLaga
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.MyLaga
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LagaKamariPresenterTest {

    @Mock
    private
    lateinit var view: MyLagaView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: DataRepository

    private lateinit var presenter: LagaKamariPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = LagaKamariPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun testNimmLagaKamari(){
        var lagaKamaris: MutableList<MyLaga> = mutableListOf()
        var response = ListOfMyLaga(lagaKamaris)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .machenRequest(TheSportDBApi.nimmLastMatch("4328")).await(),
                ListOfMyLaga::class.java
            )).thenReturn(response)

            presenter.nimmLagaKamari("4328",true)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(lagaKamaris)
            Mockito.verify(view).hideLoading()
        }
    }
}

