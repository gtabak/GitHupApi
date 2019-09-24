package com.gtabak.ingcase.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.gtabak.ingcase.R

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_layout)

        if (isNetworkAvailable()) {

            Handler().postDelayed({
                startActivity(Intent(this, HomeScreen::class.java))
                finish()
            }, SPLASH_TIME_OUT)

        } else {

            Toast.makeText(
                applicationContext,
                resources.getString(R.string.connection_txt),
                Toast.LENGTH_LONG
            ).show()

        }

    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}
