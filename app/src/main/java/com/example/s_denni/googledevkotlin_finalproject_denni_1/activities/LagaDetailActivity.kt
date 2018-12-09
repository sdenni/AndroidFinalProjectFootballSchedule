package com.example.s_denni.googledevkotlin_finalproject_denni_1.activities

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.R.attr.colorAccent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.LagaDetailView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.drawable.ic_add_to_favorites
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.drawable.ic_added_to_favorites
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.id.nambihan_karesep
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R.menu.detail_menu
import com.example.s_denni.googledevkotlin_finalproject_denni_1.databases.database
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.*
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters.DetailKlubPresenter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.invisible
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.centerHorizontally
import org.jetbrains.anko.centerVertically
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class LagaDetailActivity : AppCompatActivity(), LagaDetailView {

    private lateinit var detailPresenter: DetailKlubPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

//    myLagaInfo: ListOfMyLaga,
//    kencaKlubData: KlubSepakBolaResponse,
//    katuhuKlubData: KlubSepakBolaResponse

    private lateinit var dataof_myLagaInfo: MyLaga
    private lateinit var dataof_kencaKlubData: KlubSepakBola
    private lateinit var dataof_katuhuKlubData: KlubSepakBola

    private lateinit var tv_tanggalmaen: TextView

    private lateinit var iv_klub_c: ImageView
    private lateinit var iv_klub_k: ImageView
    private lateinit var tv_kenca: TextView
    private lateinit var tv_katuhu: TextView

    private lateinit var tvGoalDetailsC: TextView
    private lateinit var tvGoalDetailsK: TextView
    private lateinit var tvShotsC: TextView
    private lateinit var tvShotsK: TextView
    private lateinit var tvKeeperC: TextView
    private lateinit var tvKeeperK: TextView
    private lateinit var tvDefenseC: TextView
    private lateinit var tvDefenseK: TextView
    private lateinit var tvMidfieldC: TextView
    private lateinit var tvMidfieldK: TextView
    private lateinit var tvForwardC: TextView
    private lateinit var tvForwardK: TextView
    private lateinit var tvSubstituteC: TextView
    private lateinit var tvSubstituteK: TextView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idSchedule: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        idSchedule = intent.getStringExtra("id_schedule")
        supportActionBar?.title = "Laga Informasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        verticalLayout(){

            textView {
                text = "Tanggal"
                id = R.id.tanggalMaen
                textSize = 20f
                textAlignment = left
            }.lparams{
                margin = dip(3)
            }

            swipeRefresh = swipeRefreshLayout {
//                                setColorSchemeResources(
//                    colorAccent,
//                    android.R.color.holo_green_light,
//                    android.R.color.holo_orange_light,
//                    android.R.color.holo_red_light
//                )

                scrollView {
                    isVerticalScrollBarEnabled = false

                    verticalLayout () {
                        lparams(width = matchParent, height = wrapContent)

                        //LAYOUT
                        linearLayout(){
                            verticalLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                imageView{
                                    id = R.id.image_klub_c
                                }.lparams(width= dip(75), height = dip(75))

                                textView {
                                    text = "Kenca Club"
                                    id = R.id.kenca_club
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "VS"
                                    textSize = 20f
                                }

                            }
                            verticalLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                imageView{
                                    id = R.id.image_klub_k
                                }.lparams(width= dip(75), height = dip(75))

                                textView {
                                    text = "Katuhu Klub"
                                    id = R.id.katuhu_club
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        /// #1--- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goals_details_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Goals"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goals_details_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.shots_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Shots"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.shots_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        textView {
                            text = "Informasi Tambahan"
                            textSize = 16f
                        }.lparams{
                            margin = dip(3)
                        }

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goal_keeper_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Goal Keeper"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goal_keeper_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.defense_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Defense"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.defense_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.midfield_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Midfield"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.midfield_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.forward_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Forward"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.forward_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.substitute_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Substitute"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.substitute_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        progressBar = progressBar {
                        }.lparams {
//                            centerHorizontally()
                        }
                    }
                }

            }



            // --- //
        }

        tv_tanggalmaen = find(R.id.tanggalMaen)

        iv_klub_c = find(R.id.image_klub_c)
        iv_klub_k = find(R.id.image_klub_k)
        tv_kenca = find(R.id.kenca_club)
        tv_katuhu = find(R.id.katuhu_club)

        tvGoalDetailsC = find(R.id.goals_details_c)
        tvGoalDetailsK = find(R.id.goals_details_k)
        tvShotsC = find(R.id.shots_c)
        tvShotsK = find(R.id.shots_k)
        tvKeeperC = find(R.id.goal_keeper_c)
        tvKeeperK = find(R.id.goal_keeper_k)
        tvDefenseC = find(R.id.defense_c)
        tvDefenseK = find(R.id.defense_k)
        tvMidfieldC = find(R.id.midfield_c)
        tvMidfieldK = find(R.id.midfield_k)
        tvForwardC = find(R.id.forward_c)
        tvForwardK = find(R.id.forward_k)
        tvSubstituteC = find(R.id.substitute_c)
        tvSubstituteK = find(R.id.substitute_k)

        favoriteState()
        val request = DataRepository()
        val gson = Gson()
        detailPresenter = DetailKlubPresenter(this, request, gson)
        detailPresenter.nimmKlubDetail(idSchedule)

        swipeRefresh.onRefresh {
            detailPresenter.nimmKlubDetail(idSchedule)
        }

    }

    private fun favoriteState(){
        database.use {
            val result = select(Kareseps.TABLE_KARESEP)
                .whereArgs("(ID_SCHEDULE = {id})",
                    "id" to idSchedule)

            val favorite = result.parseList(classParser<Kareseps>())

            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    //Team
//    private fun favoriteState(){
//        database.use {
//            val result = select(Favorite.TABLE_FAVORITE)
//                .whereArgs("(TEAM_ID = {id})",
//                    "id" to id)
//            val favorite = result.parseList(classParser<Favorite>())
//            if (!favorite.isEmpty()) isFavorite = true
//        }
//    }

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
                insert(Kareseps.TABLE_KARESEP,
                    Kareseps.ID_SCHEDULE to idSchedule,
                    Kareseps.ID_KENCA to dataof_myLagaInfo.idKlubKenca,
                    Kareseps.ID_KATUHU to dataof_myLagaInfo.idKlubKatuhu,
                    Kareseps.KENCA_NAME to dataof_kencaKlubData.teamName,
                    Kareseps.KATUHU_NAME to dataof_katuhuKlubData.teamName,
                    Kareseps.KENCA_SCORE to dataof_myLagaInfo.lagaHasilKenca,
                    Kareseps.KATUHU_SCORE to dataof_myLagaInfo.lagaHasilKatuhu,
                    Kareseps.TANGGAL_LIGA to dataof_myLagaInfo.tanggalNa)
            }
            swipeRefresh.snackbar("Added to favorite").show()

        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Kareseps.TABLE_KARESEP, "(ID_SCHEDULE = {id})",
                    "id" to idSchedule)
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



    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(
        myLagaInfo: ListOfMyLaga,
        kencaKlubData: KlubSepakBolaResponse,
        katuhuKlubData: KlubSepakBolaResponse
    ) {

        dataof_myLagaInfo = myLagaInfo.events[0]
        dataof_kencaKlubData = kencaKlubData.teams[0]
        dataof_katuhuKlubData = katuhuKlubData.teams[0]

        tv_tanggalmaen.text = dataof_myLagaInfo.tanggalNa

        tv_kenca.text = dataof_kencaKlubData.teamName
        tv_katuhu.text = dataof_katuhuKlubData.teamName

        Picasso.get().load(dataof_kencaKlubData.teamBadge).into(iv_klub_c)
        Picasso.get().load(dataof_katuhuKlubData.teamBadge).into(iv_klub_k)

        tvGoalDetailsC.text = dataof_myLagaInfo.goalDetailC
        tvGoalDetailsK.text = dataof_myLagaInfo.goalDetailK
        tvShotsC.text = dataof_myLagaInfo.lagaHasilKenca
        tvShotsK.text = dataof_myLagaInfo.lagaHasilKatuhu
        tvKeeperC.text = dataof_myLagaInfo.goalKeeperC
        tvKeeperK.text = dataof_myLagaInfo.goalKeeperK
        tvDefenseC.text = dataof_myLagaInfo.defenseC
        tvDefenseK.text = dataof_myLagaInfo.defenseK
        tvMidfieldC.text = dataof_myLagaInfo.midFieldC
        tvMidfieldK.text = dataof_myLagaInfo.midFieldK
        tvForwardC.text = dataof_myLagaInfo.forwardC
        tvForwardK.text = dataof_myLagaInfo.forwardK
        tvSubstituteC.text = dataof_myLagaInfo.substituteC
        tvSubstituteK.text = dataof_myLagaInfo.substituteK
    }
}