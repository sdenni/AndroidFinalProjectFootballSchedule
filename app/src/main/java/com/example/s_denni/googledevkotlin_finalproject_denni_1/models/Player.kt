package com.example.s_denni.googledevkotlin_finalproject_denni_1.models

import com.google.gson.annotations.SerializedName

data class Player (

    @SerializedName("idPlayer")
    var idPlayer: String? = null,

    @SerializedName("strPlayer")
    var strName: String? = null,

    @SerializedName("strPosition")
    var strPosition: String? = null,

    @SerializedName("strCutout")
    var image: String? = null,

    @SerializedName("strFanart1")
    var background_image: String? = null,

    @SerializedName("strHeight")
    var strHeight: String? = null,

    @SerializedName("strWeight")
    var strWeight: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescription: String? = null

)