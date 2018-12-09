package com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.MyLaga
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class LagaKamariAdapter (private val lagas: List<MyLaga>, private val listener: (MyLaga) -> Unit)
    : RecyclerView.Adapter<KamariViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KamariViewHolder {
        return KamariViewHolder(KamariUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = lagas.size

    override fun onBindViewHolder(holder: KamariViewHolder, position: Int) {
        holder.bindItem(lagas[position], listener)
    }
}

class KamariViewHolder(view: View) : RecyclerView.ViewHolder(view){

    public val main_layout: LinearLayout = view.find(R.id.main_layout)
    private val tanggalMaen: TextView = view.find(R.id.tanggalMaen)
    private val klubKenca: TextView = view.find(R.id.kenca_club)
    private val klubKatuhu: TextView = view.find(R.id.katuhu_club)
    private val klubScoreKenca: TextView = view.find(R.id.kenca_club_scr)
    private val klubScoreKatuhu: TextView = view.find(R.id.katuhu_club_scr)

    fun bindItem(laga: MyLaga, listener: (MyLaga) -> Unit) {

        val string = laga.tanggalNa
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date: Date = format.parse(string)

        val pattern = "EEEE, dd-MMM-yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)

        val date_string = simpleDateFormat.format(date)

//        var informasiLagaClass = Information(laga.idKlubKenca,laga.idKlubKatuhu)

        tanggalMaen.text = date_string
        klubKenca.text = laga.lagaKlubNameKenca
        klubKatuhu.text = laga.lagaKlubNameKatuhu
        klubScoreKenca.text = laga.lagaHasilKenca
        klubScoreKatuhu.text = laga.lagaHasilKatuhu

//        LastMatchAdapter.lagaKamari = laga
//        LastMatchAdapter.informasiLaga = informasiLagaClass

        itemView.setOnClickListener {
            listener(laga)
        }
    }
}


class KamariUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                id = R.id.main_layout
                padding = dip(5)
//                backgroundColor = R.color.colorAccent
                orientation = LinearLayout.VERTICAL

                textView() {
                    text = "tanggal"
                    id = R.id.tanggalMaen
                    textSize = 20f
                }

                linearLayout() {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout() {
                        lparams(width = 0, height = wrapContent, weight = 0.4F)
                        padding = dip(1)

                        textView {
                            text = "Kenca Club"
                            id = R.id.kenca_club
                            textSize = 16f
                            textAlignment = left
                        }.lparams{
                            margin = dip(3)
                        }

                        textView {
                            text = "scr"
                            id = R.id.kenca_club_scr
                            textSize = 16f
                            textAlignment = right
                        }.lparams{
                            margin = dip(2)
                        }
                    }

                    linearLayout(){
                        lparams(width = 0, height = wrapContent, weight = 0.2F)
                        textView {
                            text = "VS"
                            textSize = 20f
                        }.lparams{
                            margin = dip(5)
                        }
                    }

                    linearLayout() {
                        lparams(width = 0, height = wrapContent, weight = 0.4F)
                        padding = dip(1)

                        textView {
                            text = "scr"
                            id = R.id.katuhu_club_scr
                            textSize = 16f
                            textAlignment = left
                        }.lparams{
                            margin = dip(2)
                        }

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
            }
        }
    }
}