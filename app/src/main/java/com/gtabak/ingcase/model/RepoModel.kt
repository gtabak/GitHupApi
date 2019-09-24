package com.gtabak.ingcase.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoModel(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: OwnerModel,
    @SerializedName("stargazers_count") val stargazers_count: Int,
    @SerializedName("open_issues_count") val open_issues_count: Int
) : Serializable