package com.example.s_denni.googledevkotlin_finalproject_denni_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.layout.activity_main
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.id.*
import com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments.FavoritesFragment
import com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments.MatchesFragment
import com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matches_b -> {
                    loadMatches(savedInstanceState)
                }
                teams_b -> {
                    loadTeams(savedInstanceState)
                }
                favs_b -> {
                    loadFavs(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matches_b
    }

    private fun loadMatches(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchesFragment(), MatchesFragment::class.simpleName)
                .commit()
        }
    }

    private fun loadTeams(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.simpleName)
                .commit()
        }
    }

    private fun loadFavs(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.simpleName)
                .commit()
        }
    }
}
