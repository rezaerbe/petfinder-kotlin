package com.erbeandroid.petfinder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.erbeandroid.petfinder.feature.animal.common.launchAndCollectIn
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectionMonitoring: ConnectionMonitoring

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectionMonitoring.networkStatus.launchAndCollectIn(this) { state ->
            Log.d("TAG", state.toString())
        }
    }
}