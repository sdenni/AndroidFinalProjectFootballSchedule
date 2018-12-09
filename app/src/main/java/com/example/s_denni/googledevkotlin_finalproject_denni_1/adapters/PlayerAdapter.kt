package com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerAdapter (private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewAdapter>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewAdapter {
        return PlayerViewAdapter(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewAdapter, position: Int) {
        holder.bindItem(players[position], listener)
    }
}

class PlayerViewAdapter(view: View) : RecyclerView.ViewHolder(view){

    private val playerIcon: ImageView = view.find(R.id.playerIco)
    private val playerName: TextView = view.find(R.id.playerName)
    private val playerPosition: TextView = view.find(R.id.playerPos)

    fun bindItem(player: Player, listener: (Player) -> Unit) {

        Picasso.get().load(player.image).into(playerIcon)
        playerName.text = player.strName
        playerPosition.text = player.strPosition

        itemView.setOnClickListener { listener(player) }
    }
}


class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout() {
                lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(10)
                }
                id = R.id.main_layout
                padding = dip(5)
//                backgroundColor = R.color.colorAccent
                orientation = LinearLayout.HORIZONTAL

                imageView() {
                    id = R.id.playerIco
                }.lparams() {
                    width = dip(50)
                    height = dip(50)
                }

                textView() {
                    text = "Name"
                    id = R.id.playerName
                    textSize = 20f
                }.lparams() {
                    leftMargin = dip(10)
                    rightMargin = dip(10)
                }

                textView() {
                    text = "Name"
                    id = R.id.playerPos
                    textSize = 20f
                }

//                linearLayout() {
//                    orientation = LinearLayout.HORIZONTAL
//
//                    linearLayout() {
//                        lparams(width = 0, height = wrapContent, weight = 0.4F)
//                        padding = dip(1)
//
//                        textView {
//                            text = "Kenca Club"
//                            id = R.id.kenca_club
//                            textSize = 16f
//                            textAlignment = left
//                        }.lparams{
//                            margin = dip(3)
//                        }
//
//                        textView {
//                            text = "scr"
//                            id = R.id.kenca_club_scr
//                            textSize = 16f
//                            textAlignment = right
//                        }.lparams{
//                            margin = dip(2)
//                        }
//                    }
//
//                    linearLayout(){
//                        lparams(width = 0, height = wrapContent, weight = 0.2F)
//                        textView {
//                            text = "VS"
//                            textSize = 20f
//                        }.lparams{
//                            margin = dip(5)
//                        }
//                    }
//
//                    linearLayout() {
//                        lparams(width = 0, height = wrapContent, weight = 0.4F)
//                        padding = dip(1)
//
//                        textView {
//                            text = "scr"
//                            id = R.id.katuhu_club_scr
//                            textSize = 16f
//                            textAlignment = left
//                        }.lparams{
//                            margin = dip(2)
//                        }
//
//                        textView {
//                            text = "Katuhu Klub"
//                            id = R.id.katuhu_club
//                            textSize = 16f
//                            textAlignment = right
//                        }.lparams{
//                            margin = dip(5)
//                        }
//                    }
//                }
            }
        }
    }
}