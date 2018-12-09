package com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.string.last_matches_t
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.string.next_matches_t
import com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters.MyFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_pertandingan.*

class MatchesFragment: Fragment() {

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
        tabMatchAdapter.add(NextMatchesFragment(), resources.getString(next_matches_t))
        tabMatchAdapter.add(LastMatchesFragment(), resources.getString(last_matches_t))
        viewpager_main.adapter = tabMatchAdapter
        tabs_main.setupWithViewPager(viewpager_main)
    }

}