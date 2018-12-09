package com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MyFragmentPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    private val namaTabs: ArrayList<String> = ArrayList()
    private val fragments: ArrayList<Fragment> = ArrayList()

    fun add(fragment: Fragment, title:String){
        namaTabs.add(title)
        fragments.add(fragment)
    }


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return namaTabs[position]
    }

}