package com.example.s_denni.googledevkotlin_finalproject_denni_1.networks

import android.net.Uri
import com.example.s_denni.googledevkotlin_finalproject_denni_1.BuildConfig

class TheSportDBApi {

    companion object {

        fun getTeams(league: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("search_all_teams.php")
                    .appendQueryParameter("l", league)
                    .build()
                    .toString()
        }

        fun getTeamsByName(teams: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchteams.php")
                .appendQueryParameter("t", teams)
                .build()
                .toString()
        }

        fun nimmKlubInfo(data: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", data)
                .build()
                .toString()
        }

        fun nimmMatch(id_schedule: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupevent.php")
                .appendQueryParameter("id", id_schedule)
                .build()
                .toString()
        }

        fun nimmNextMatch(leagueId: String?): String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("eventsnextleague.php")
                    .appendQueryParameter("id", leagueId)
                    .build()
                    .toString()
        }

        fun nimmLastMatch(leagueId: String?): String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("eventspastleague.php")
                    .appendQueryParameter("id", leagueId)
                    .build()
                    .toString()
        }

        fun nimmMatchByLeagueName(leagueName: String?) : String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchevents.php")
                .appendQueryParameter("e", leagueName)
                .build()
                .toString()
        }

        fun nimmPlayerKlub(KlubName: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchplayers.php")
                .appendQueryParameter("t", KlubName)
                .build()
                .toString()
        }

        fun nimmPlayer(id_player: String?) : String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupplayer.php")
                .appendQueryParameter("id", id_player)
                .build()
                .toString()
        }


    }
}