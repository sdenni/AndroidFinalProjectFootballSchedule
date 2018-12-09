package com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters

import android.support.design.R.attr.color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.MyLaga
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.*
import org.jetbrains.anko.*

class LagaEnjingAdapter (private val lagas: List<MyLaga>, private val listener: (MyLaga) -> Unit)
    : RecyclerView.Adapter<EnjingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnjingViewHolder {
        return EnjingViewHolder(EnjingUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = lagas.size

    override fun onBindViewHolder(holder: EnjingViewHolder, position: Int) {
        holder.bindItem(lagas[position], listener)
    }
}

class EnjingViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val main_layout: LinearLayout = view.find(R.id.main_layout)
    private val tanggalMaen: TextView = view.find(R.id.tanggalMaen)
    private val waktuMaen: TextView = view.find(R.id.waktuMaen)
    private val klubKenca: TextView = view.find(R.id.kenca_club)
    private val klubKatuhu: TextView = view.find(R.id.katuhu_club)
    private val klubScoreKenca: TextView = view.find(R.id.kenca_club_scr)
    private val klubScoreKatuhu: TextView = view.find(R.id.katuhu_club_scr)

    fun bindItem(laga: MyLaga, listener: (MyLaga) -> Unit) {

        val date_string = laga.tanggalNa?.let { ubahFormatTanggal(it) }
        val time_string = laga.timeNa?.let { ubahFormatWaktu(it) }

        tanggalMaen.text = date_string.toString()
        waktuMaen.text = time_string.toString()
        klubKenca.text = laga.lagaKlubNameKenca
        klubKatuhu.text = laga.lagaKlubNameKatuhu
        klubScoreKenca.text = laga.lagaHasilKenca
        klubScoreKatuhu.text = laga.lagaHasilKatuhu

        itemView.setOnClickListener { listener(laga) }
    }
}


class EnjingUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                id = R.id.main_layout
                padding = dip(5)
                orientation = LinearLayout.VERTICAL

                textView {
                    text = "tanggal"
                    id = R.id.tanggalMaen
                    textSize = 20f
                    gravity = R.id.center_horizontal
                    backgroundColor = ContextCompat.getColor(context,R.color.colorChoosen)
                }.lparams {
                    width = matchParent
                    height = wrapContent

                }

                textView {
                    text = "waktu"
                    id = R.id.waktuMaen
                    textSize = 20f
                    gravity = R.id.center_horizontal
                    backgroundColor = ContextCompat.getColor(context,R.color.colorChoosen)
                }.lparams {
                    width = matchParent
                    height = wrapContent

                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = R.id.center_horizontal
                    backgroundColor = ContextCompat.getColor(context,R.color.colorPrimaryDark)

                    linearLayout() {
                        lparams(width = 0, height = wrapContent, weight = 0.4F)
                        padding = dip(1)

                        textView {
                            text = "Kenca Club"
                            id = R.id.kenca_club
                            textSize = 16f
                            gravity = R.id.center_horizontal
                            textColor =  ContextCompat.getColor(context,R.color.colorSoftText)
                        }.lparams{
                            margin = dip(3)
                            width = matchParent
                        }

                        textView {
                            text = "scr"
                            id = R.id.kenca_club_scr
                            textSize = 16f
                            gravity = R.id.center_horizontal
                            textColor =  ContextCompat.getColor(context,R.color.colorSoftText)
                        }.lparams{
                            margin = dip(2)
                            width = matchParent
                        }
                    }.lparams{
//                        width = matchParent
                    }

                    linearLayout(){
                        lparams(width = 0, height = wrapContent, weight = 0.2F)
                        textView {
                            text = "VS"
                            textSize = 20f
                            gravity = R.id.center_horizontal
                            textColor =  ContextCompat.getColor(context,R.color.colorSoftText)
                        }.lparams{
                            margin = dip(5)
                            width = matchParent
                        }
                    }.lparams{
//                        width = matchParent
                    }

                    linearLayout() {
                        lparams(width = 0, height = wrapContent, weight = 0.4F)
                        padding = dip(1)

                        textView {
                            text = "scr"
                            id = R.id.katuhu_club_scr
                            textSize = 16f
                            gravity = R.id.center_horizontal
                            textColor =  ContextCompat.getColor(context,R.color.colorSoftText)
                        }.lparams{
                            margin = dip(2)
                            width = matchParent
                        }

                        textView {
                            text = "Katuhu Klub"
                            id = R.id.katuhu_club
                            textSize = 16f
                            gravity = R.id.center_horizontal
                            textColor =  ContextCompat.getColor(context,R.color.colorSoftText)
                        }.lparams{
                            margin = dip(5)
                            width = matchParent
                        }
                    }.lparams{
//                        width = matchParent
                    }
                }.lparams{
                    width = matchParent
                    leftMargin = dip(5)
                    rightMargin = dip(5)
                    bottomMargin = dip(15)
                }
            }
        }
    }
}