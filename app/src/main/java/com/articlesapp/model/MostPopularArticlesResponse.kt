package com.articlesapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(
    val uri: String? = "",
    val url: String? = "",
    val id: Long? = null,
    val assetId: Long = 0,
    val byline: String = "",
    val source: String = "",
    @SerializedName("published_date")
    @Expose
    val publishedDate: String? = "",
    val title: String? = "",
    val desFacet: List<String>? = null,
    val orgFacet: List<String>? = null,
    val perFacet: List<String>? = null,
    val geoFacet: List<String>? = null,
    val media: List<Medium>? = null
):Serializable

data class Medium(

    val type: String? = "",
     val subtype: String? = "",
     val caption: String? = "",
     val copyright: String? = "",
     val approvedForSyndication: Int? = 0,
    @SerializedName("media-metadata")
    @Expose
     val mediaMetadata: List<MediaMetaDatum>? = null
):Serializable
data class MediaMetaDatum(
    val url: String? = "",
     val format: String? = "",
     val height: Int? = 0,
     val width: Int? = 0
):Serializable

data class MostPopularArticlesResponse(
    var status: String? = "",
    val copyright: String? = "",
    val numResults: Int? = 0,
    var results: List<Result>? = null
):Serializable