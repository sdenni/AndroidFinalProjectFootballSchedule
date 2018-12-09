package com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamInformationFragment_Backup : Fragment(), AnkoComponent<Context> {

    lateinit var deskripsi : String

    lateinit var desk_tv : TextView

    companion object {
//        fun newInstance(desk : String) : TeamInformationFragment {
//            val fragment = TeamInformationFragment()
//            val args = Bundle()
//            fragment.arguments = args
//            return fragment
//        }
//        fun newInstance(desk : String) : TeamInformationFragment {
//            val fragment = TeamInformationFragment()
//            val args = Bundle()
//            fragment.arguments = args
//            return fragment
//        }
        @JvmStatic
        fun newInstance(desk: String) = TeamInformationFragment_Backup().apply {
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
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        Log.d("TRACE", "createView")
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = android.widget.LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                desk_tv = textView{
                    text = "Something For You"
                    id = R.id.deskripsi
                    textSize = 50f
                }
            }
//            desk_tv = textView()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("TRACE", "onActivityCreated")
        super.onActivityCreated(savedInstanceState)

        desk_tv = find(R.id.deskripsi)
//        Log.d("TRACE", "data "+deskripsi)
        desk_tv.text = deskripsi
    }
}