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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LagaEnjingPresenterTest {
    @Mock
    private
    lateinit var view: MyLagaView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: DataRepository

    private lateinit var presenter: LagaEnjingPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = LagaEnjingPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun testNimmLagaEnjing(){
        var lagaEnjings: MutableList<MyLaga> = mutableListOf()
        var response = ListOfMyLaga(lagaEnjings)

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .machenRequest(TheSportDBApi.nimmLastMatch("4328")).await(),
                    ListOfMyLaga::class.java
                )
            ).thenReturn(response)

            presenter.nimmLagaEnjing("4328", true)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(lagaEnjings)
            Mockito.verify(view).hideLoading()
        }

    }
}