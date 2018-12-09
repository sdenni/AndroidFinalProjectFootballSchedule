package com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import org.jetbrains.anko.support.v4.find

class TeamInformationFragment : Fragment() {

    lateinit var deskripsi : String

    lateinit var desk_tv : TextView

    companion object {
        @JvmStatic
        fun newInstance(desk: String?) = TeamInformationFragment().apply {
            arguments = Bundle().apply {
                putString("desk", desk)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getString("desk")?.let {
            deskripsi = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.detail_klub_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        desk_tv = find(R.id.deskripsi)
        desk_tv.text = deskripsi
    }
}