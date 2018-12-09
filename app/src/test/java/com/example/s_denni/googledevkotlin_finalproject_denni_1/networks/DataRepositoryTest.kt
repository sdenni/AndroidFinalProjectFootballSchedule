package com.example.s_denni.googledevkotlin_finalproject_denni_1.networks

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class DataRepositoryTest {
    @Test
    fun testMachenRequest(){
        val apiRepository = mock(DataRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.machenRequest(url)
        verify(apiRepository).machenRequest(url)
    }
}