package com.gtabak.ingcase.viewmodel

import com.gtabak.ingcase.model.RepoModel
import com.gtabak.ingcase.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class HomeScreenViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var dataList: MutableLiveData<List<RepoModel>>
    private lateinit var mUserName: MutableLiveData<String>

    fun getData(userName: String): MutableLiveData<List<RepoModel>> {

        dataList = MutableLiveData()
        loadData(userName)
        return dataList
    }

    private fun loadData(userName: String) {
        val call: Call<List<RepoModel>> = ApiClient.getClient.getRepos(userName)
        call.enqueue(object : Callback<List<RepoModel>> {

            override fun onResponse(call: Call<List<RepoModel>>?, response: Response<List<RepoModel>>?) {
                if(response!!.isSuccessful){
                    dataList.value = response.body()!!
                }else{
                    dataList.value = null
                }
            }

            override fun onFailure(call: Call<List<RepoModel>>?, t: Throwable?) {

                dataList.value = null

            }

        })
    }

}