package com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.activities.PlayerDetailActivity
import com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters.PlayerAdapter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.PlayerView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.Player
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters.PlayerPresenter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.invisible
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamPlayerFragment : Fragment(), AnkoComponent<Context>, PlayerView {

    companion object {
        @JvmStatic
        fun newInstance(selectedname: String?) = TeamPlayerFragment().apply {
            arguments = Bundle().apply {
                putString("team_selected", selectedname)
            }
        }
    }

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapterPlayer: PlayerAdapter
    private lateinit var reusableView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var textView: TextView

    lateinit var team_name : String

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getString(resources.getString(R.string.key_team_selected))?.let {
            team_name = it
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        reusableView = find(R.id.reusable_view)

        adapterPlayer = PlayerAdapter(players) {
            context?.startActivity<PlayerDetailActivity>(resources.getString(R.string.key_id_player) to "${it.idPlayer}")
        }

        reusableView.adapter = adapterPlayer
        val request = DataRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.nimmPlayer(team_name)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){

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
                        id = R.id.reusable_view
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = android.support.v7.widget.LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(myPlayers: List<Player>) {
        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(myPlayers)
        adapterPlayer.notifyDataSetChanged()
    }
}