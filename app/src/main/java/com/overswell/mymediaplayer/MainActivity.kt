package com.overswell.mymediaplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.start(this)

        button.setOnClickListener {
            vm.play()
        }
        button2.setOnClickListener {
            vm.stop()
        }
    }

}

class MainViewModel : ViewModel() {

    lateinit var context: Context
    private var p: MediaPlayer? = null

    fun start(context: Context) {
        this.context = context
        p = MediaPlayer.create(context, R.raw.kid)

    }

    fun play() {
        p?.let {
            if (it.isPlaying) {
                it.seekTo(0)
            }
            else {
                it.start()
            }
        }
    }

    fun stop() {
        p?.pause()
    }

    override fun onCleared() {
        super.onCleared()
        p?.release()
    }

}