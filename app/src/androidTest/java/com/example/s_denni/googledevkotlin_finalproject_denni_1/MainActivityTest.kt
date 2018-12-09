package com.example.s_denni.googledevkotlin_finalproject_denni_1

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.id.*
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecycleViewBehaviour(){

        Thread.sleep(5000)
        onView(withId(list_nextMatch))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(list_nextMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(1000)
        onView(withId(list_nextMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(13, click()))
        Thread.sleep(5000)

    }

    @Test
    fun testAppBehaviour() {
        //wait network for 5s
        Thread.sleep(5000)
        //cek if list laga nextMatch is displayed
        onView(withId(list_nextMatch))
            .check(matches(isDisplayed()))
        //klik bottom menu kareseps
        onView(withId(favs_b))
            .perform(click())
        //wait select for 2.5s
        Thread.sleep(2500)
        //klik bottom menu kamari
        onView(withId(matches_b))
            .perform(click())
        //wait network for 5s
        Thread.sleep(5000)
        //scroll recycle view ke posisi 10
        onView(withId(list_nextMatch))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        //wait for 1s
        Thread.sleep(1000)
        //klik item view posisi ke 2
        onView(withId(list_nextMatch))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
//        wait network for 5s
        Thread.sleep(5000)
        //clik favorite untuk menambahkan favorite
        onView(withId(nambihan_karesep))
            .perform(click())
        //wait for 1s
        Thread.sleep(1000)
//        //press back
        Espresso.pressBack()
        //wait netwrok for 1s
        Thread.sleep(1000)
        //Go to list team
        onView(withId(teams_b))
            .perform(click())
        //wait network for 1s
        Thread.sleep(1000)
        //cek if list team is displayed
        onView(withId(list_team))
            .check(matches(isDisplayed()))
        //wait network for 5s
        Thread.sleep(5000)
        //sroll recycle to 7 and perform click
        onView(withId(list_team))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
        //wait network for 5s
        Thread.sleep(5000)
        //set to favorite
        //clik favorite untuk menambahkan favorite
        onView(withId(nambihan_karesep))
            .perform(click())
        //wait network for 1.5s
        Thread.sleep(1500)
        //press back
        Espresso.pressBack()
        //wait network for 2s
        Thread.sleep(2000)
        //go to list fav
        onView(withId(favs_b))
            .perform(click())
        //wait select for 2.5s
        Thread.sleep(2500)
    }

}