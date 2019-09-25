package com.gtabak.ingcase.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.gtabak.ingcase.R
import com.gtabak.ingcase.adapter.HomeListAdapter
import com.gtabak.ingcase.databinding.HomeScreenLayoutBinding
import com.gtabak.ingcase.model.RepoModel
import com.gtabak.ingcase.room_database.AppDatabase
import com.gtabak.ingcase.viewmodel.HomeScreenViewModel

class HomeScreen : AppCompatActivity() {

    private var dataList = ArrayList<RepoModel>()
    private var userName = String()
    private lateinit var adapter: HomeListAdapter
    private var db: AppDatabase? = null
    private var REQUEST_CODE: Int = 1
    private var binding: HomeScreenLayoutBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.home_screen_layout)

        val model = ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "repos")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


        binding!!.submitButton.setOnClickListener {
            userName = binding!!.userNameTxt.text.toString()
            hideKeyboard()
            binding!!.llProgressBar.root.visibility = View.VISIBLE

            if (userName.isNotEmpty()) {

                model.getData(userName).observe(this, Observer<List<RepoModel>> { users ->

                    if (users!!.isEmpty()) {
                        dataList.clear()

                        Toast.makeText(
                            applicationContext,
                            resources.getString(R.string.not_found),
                            Toast.LENGTH_LONG
                        ).show()

                    } else {

                        dataList = users as ArrayList<RepoModel>

                    }
                    updateUI(dataList, binding!!)


                    binding!!.llProgressBar.root.visibility = View.GONE
                })

            } else {

                Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.empty_txt),
                    Toast.LENGTH_LONG
                ).show()
                binding!!.llProgressBar.root.visibility = View.GONE

            }

        }
    }

    private fun updateUI(items: List<RepoModel>, mBinding: HomeScreenLayoutBinding) {

        adapter = HomeListAdapter(items, db) { pos ->
            val intent = Intent(this, DetailScreen::class.java)
            intent.putExtra("DTO", items[pos])
            startActivityForResult(intent, REQUEST_CODE)
            //startActivity(intent)
        }
        mBinding.recyclerView.adapter = adapter
    }

    private fun hideKeyboard() {
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.SHOW_FORCED
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE -> {

                updateUI(dataList, binding!!)

            }
        }
    }

}