package com.example.s_denni.googledevkotlin_finalproject_denni_1.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.PlayerDetailView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.ListOfPlayers
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.Player
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters.DetailPlayerPresenter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.invisible
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_klub.*
import kotlinx.android.synthetic.main.activity_player_detail.*
import org.jetbrains.anko.find

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView{
    private lateinit var presenter: DetailPlayerPresenter
    private lateinit var player: Player
    private lateinit var progressBar: ProgressBar
    //    private lateinit var swipeRefresh: SwipeRefreshLayout


    private lateinit var playerBackgroud: ImageView
    private lateinit var playerName: TextView
    private lateinit var playerWeight: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerPosision: TextView
    private lateinit var playerDescription: TextView

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val intent = intent
        id = intent.getStringExtra("id_player")
        Log.d("TRACE", "ID "+id)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = find(R.id.progressBar)

        playerBackgroud = find(R.id.iv_playerBackground)
        playerName = find(R.id.tv_player_name)
        playerWeight= find(R.id.tv_player_w)
        playerHeight= find(R.id.tv_player_h)
        playerPosision= find(R.id.tv_player_pos)
        playerDescription= find(R.id.tv_player_desc)


        val request = DataRepository()
        val gson = Gson()
        presenter = DetailPlayerPresenter(this, request, gson)
        presenter.nimmDetailPlayer(id)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
    override fun showEventList(playerData: ListOfPlayers) {

//        Log.d("TRACE", "Some data "+id)
//        Log.d("TRACE", "Some data "+playerData.players[0].strName)

        var myPlayer: Player = playerData.players[0]

        Picasso.get().load(myPlayer.background_image).into(playerBackgroud)
        playerName.text = myPlayer.strName
        playerWeight.text = myPlayer.strWeight
        playerHeight.text = myPlayer.strHeight
        playerPosision.text = myPlayer.strPosition
        playerDescription.text = myPlayer.strDescription

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun showTeamDetail(data: List<Player>) {
//
//
//    }

}