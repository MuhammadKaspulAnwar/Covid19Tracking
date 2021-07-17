package com.example.basickotlin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attributes(
        @SerializedName("FID")
        val fid: Int,

        @SerializedName("Kasus_Meni")
        val meninggal: Int,

        @SerializedName("Kasus_Posi")
        val positif: Int,

        @SerializedName("Kasus_Semb")
        val sembuh: Int,

        @SerializedName("Kode_Provi")
        val kode: Int,

        @SerializedName("Provinsi")
        val provinsi: String
):Parcelable