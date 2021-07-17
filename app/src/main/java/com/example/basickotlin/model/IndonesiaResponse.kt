package com.example.basickotlin.model

import com.google.gson.annotations.SerializedName

data class IndonesiaResponse(
        @SerializedName("meninggal")
        val meninggal: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("positif")
        val positif: String,

        @SerializedName("sembuh")
        val sembuh: String
)