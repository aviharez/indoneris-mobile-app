package com.aviharez.labs.indoneris_mob_app.entity

import com.google.gson.annotations.SerializedName

data class NextEvent(
    @SerializedName("biaya") val biaya: String,
    @SerializedName("deskripsi") val deskripsi: String,
    @SerializedName("diikuti") val diikuti: Boolean,
    @SerializedName("gambar") val gambar: String,
    @SerializedName("id") val id: Int,
    @SerializedName("id_penyelenggara_acara") val id_penyelenggara_acara: Int,
    @SerializedName("judul") val judul: String,
    @SerializedName("kuota") val kuota: Int,
    @SerializedName("kuota_terisi") val kuota_terisi: Int,
    @SerializedName("penyelenggara") val penyelenggara: String,
    @SerializedName("skkm") val skkm: Int,
    @SerializedName("tanggal") val tanggal: String
)