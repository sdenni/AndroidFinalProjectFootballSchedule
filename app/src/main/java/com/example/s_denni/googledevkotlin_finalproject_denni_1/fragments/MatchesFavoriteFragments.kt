package com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.activities.LagaDetailActivity
import com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters.KaresepAdapter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.databases.database
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.Kareseps
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.id.list_laga_kareseps

import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchesFavoriteFragments : Fragment(), AnkoComponent<Context> {
    private var kareseps: MutableList<Kareseps> = mutableListOf()
    private lateinit var adapterKaresep: KaresepAdapter
    private lateinit var reusableView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var textView: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapterKaresep = KaresepAdapter(kareseps) {
            context?.startActivity<LagaDetailActivity>("id_schedule" to "${it.scheduleKode}")
        }

        reusableView.adapter = adapterKaresep

        showKaresep()

        swipeRefresh.onRefresh {
            showKaresep()
        }
    }

    override fun onResume() {
        super.onResume()
        showKaresep()
    }

    private fun showKaresep(){
        kareseps.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Kareseps.TABLE_KARESEP)
            val favorite = result.parseList(classParser<Kareseps>())
            kareseps.addAll(favorite)
            adapterKaresep.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = android.widget.LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            textView = textView()

            swipeRefresh = swipeRefreshLayout() {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    reusableView = recyclerView {
                        id = list_laga_kareseps
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = android.support.v7.widget.LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }
}