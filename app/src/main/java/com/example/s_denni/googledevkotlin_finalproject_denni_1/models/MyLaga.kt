package com.example.s_denni.googledevkotlin_finalproject_denni_1.models

import com.google.gson.annotations.SerializedName

data class MyLaga (

    @SerializedName("idEvent")
    var scheduleId: String? = null,

    @SerializedName("strEvent")
    var lagaKlubInfo: String? = null,

    @SerializedName("strAwayTeam")
    var lagaKlubNameKatuhu: String? = null,

    @SerializedName("strHomeTeam")
    var lagaKlubNameKenca: String? = null,

    @SerializedName("intHomeScore")
    var lagaHasilKenca: String? = null,

    @SerializedName("intAwayScore")
    var lagaHasilKatuhu: String? = null,

    @SerializedName("dateEvent")
    var tanggalNa: String? = null,

    @SerializedName("idHomeTeam")
    var idKlubKenca: String? = null,

    @SerializedName("idAwayTeam")
    var idKlubKatuhu: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var goalKeeperC: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var goalKeeperK: String? = null,

    @SerializedName("strHomeLineupDefense")
    var defenseC: String? = null,

    @SerializedName("strAwayLineupDefense")
    var defenseK: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var midFieldC: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var midFieldK: String? = null,

    @SerializedName("strHomeLineupForward")
    var forwardC: String? = null,

    @SerializedName("strAwayLineupForward")
    var forwardK: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var substituteC: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var substituteK: String? = null,

    @SerializedName("strHomeGoalDetails")
    var goalDetailC: String? = null,

    @SerializedName("strAwayGoalDetails")
    var goalDetailK: String? = null
)