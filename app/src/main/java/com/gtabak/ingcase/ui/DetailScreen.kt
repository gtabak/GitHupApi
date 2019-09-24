package com.gtabak.ingcase.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.gtabak.ingcase.R
import com.gtabak.ingcase.databinding.DetailScreenLayoutBinding
import com.gtabak.ingcase.model.RepoModel
import com.gtabak.ingcase.room_database.AppDatabase
import com.gtabak.ingcase.room_database.RepoEntity


class DetailScreen : AppCompatActivity() {

    private var userModel: RepoModel? = null
    private var db: AppDatabase? = null
    private var REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: DetailScreenLayoutBinding =
            DataBindingUtil.setContentView(this, R.layout.detail_screen_layout)

        if (intent.extras != null) {
            userModel = intent!!.extras!!.getSerializable("DTO") as RepoModel
            val actionBar = supportActionBar
            actionBar!!.title = userModel!!.name
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        binding.data = userModel

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "repos")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_one)
        if (db!!.repoDao().getRow(userModel!!.id) != null) {

            item.setIcon(R.drawable.ic_star_white_24dp)

        } else {

            item.setIcon(R.drawable.ic_star_border_white_24dp)

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_one) {

            if (db!!.repoDao().getRow(userModel!!.id) != null) {

                val repoEntity: RepoEntity = db!!.repoDao().getRow(userModel!!.id)
                db!!.repoDao().delete(repoEntity)
                item.setIcon(R.drawable.ic_star_border_white_24dp)

            } else {

                val repoEntity = RepoEntity(userModel!!.id)
                db!!.repoDao().insert(repoEntity)
                item.setIcon(R.drawable.ic_star_white_24dp)

            }

            val intent = Intent()
            setResult(REQUEST_CODE, intent)

            return true
        }

        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}