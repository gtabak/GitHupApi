package com.gtabak.ingcase.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OwnerModel(

    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatar_url: String
) : Serializable