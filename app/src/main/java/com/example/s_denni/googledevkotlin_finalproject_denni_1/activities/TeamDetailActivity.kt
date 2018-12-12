package com.example.s_denni.googledevkotlin_finalproject_denni_1.activities

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.design.R.attr.colorAccent
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.drawable.ic_add_to_favorites
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.drawable.ic_added_to_favorites
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.menu.detail_menu
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.id.nambihan_karesep
import com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters.MyFragmentPagerAdapter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.databases.database
import com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments.TeamInformationFragment
import com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments.TeamPlayerFragment
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.TeamDetailView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.Favorite
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.KlubSepakBola
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters.TeamDetailPresenter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.invisible
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.visible
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.string.player_t
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.string.desk_t
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by root on 2/3/18.
 */
class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: KlubSepakBola
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
//    private lateinit var teamDescription: TextView

    private lateinit var myTabs : TabLayout
    private lateinit var myView : ViewPager

    private var team_desk : String? = null
    private var klub_name : String? = null

//    private lateinit var viewpager_main :

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_klub)

        val intent = intent
        id = intent.getStringExtra("id")
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        teamBadge = find(R.id.team_badge)
        teamName = find(R.id.team_name)
        progressBar = find(R.id.progressBar)
        swipeRefresh = find(R.id.swiperefresh)
        teamFormedYear = find(R.id.tv_tahun)
        teamStadium = find(R.id.tv_stadion)
//        teamFormedYear = find(R.id.team_formed_year)
//        teamStadium = find(R.id.team_stadium)

        myView = find(R.id.viewpager_main)
        myTabs = find(R.id.tabs_main)

        favoriteState()
        val request = DataRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

        swipeRefresh.onRefresh {
            presenter.getTeamDetail(id)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<KlubSepakBola>) {
        teams = KlubSepakBola(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        team_desk = data[0].teamDescription
        klub_name = data[0].teamName
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium

        tambahTabs()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            nambihan_karesep -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                        Favorite.TEAM_ID to teams.teamId,
                        Favorite.TEAM_NAME to teams.teamName,
                        Favorite.TEAM_BADGE to teams.teamBadge)
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
            }
            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun tambahTabs(){
        val tabMatchAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        tabMatchAdapter.add(TeamInformationFragment.newInstance(team_desk), resources.getString(desk_t))
        tabMatchAdapter.add(TeamPlayerFragment.newInstance(klub_name), resources.getString(player_t))
        myView.adapter = tabMatchAdapter
        myTabs.setupWithViewPager(myView)
    }
}