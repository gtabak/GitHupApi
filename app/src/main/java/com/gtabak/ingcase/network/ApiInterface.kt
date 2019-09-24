package com.gtabak.ingcase.network

import com.gtabak.ingcase.model.RepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String?): Call<List<RepoModel>>

}