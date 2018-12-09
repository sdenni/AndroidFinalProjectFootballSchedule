package com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters.MyFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_pertandingan.*
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.string.mathes_fav_t
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.string.teams_fav_t

class FavoritesFragment: Fragment() {

    private lateinit var myView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater?.inflate(R.layout.fragment_pertandingan, container, false)

        return myView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tambahTabs()
    }

    private fun tambahTabs(){
        val tabMatchAdapter = MyFragmentPagerAdapter(childFragmentManager)
        tabMatchAdapter.add(MatchesFavoriteFragments(), resources.getString(mathes_fav_t))
        tabMatchAdapter.add(TeamsFavoriteFragment(), resources.getString(teams_fav_t))
        viewpager_main.adapter = tabMatchAdapter
        tabs_main.setupWithViewPager(viewpager_main)
    }

}