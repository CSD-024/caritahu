package com.dicoding.caritahu.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HoaxArticle(
    val id: String,
    val authors: String,
    val status: String,
    val classification: String,
    val title: String,
    val content: String,
    val fact: String,
    val references: String,
    val source_issue: String,
    val source_link: String,
    val picture1: String,
    @SerializedName("tanggal")
    val date: String,
    val tags: String,
    val conclusion: String,
) : Parcelable

data class HoaxAuthor(
    val id: String,
    @SerializedName("nama")
    val name: String,
    @SerializedName("deskripsi")
    val description: String,
    val website: String,
    val icon: String
)