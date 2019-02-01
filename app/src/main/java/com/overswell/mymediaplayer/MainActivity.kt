package com.overswell.mymediaplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.context = this

        button.setOnClickListener {
            vm.play(R.raw.kid)
        }
        button2.setOnClickListener {
            vm.stop()
        }
    }

}

class MainViewModel(val resourceArray: ResourceArray) : ViewModel() {

    lateinit var context: Context
    private var p: MediaPlayer? = null
    private val map by lazy {
        val map = HashMap<Int,MediaPlayer>()
        resourceArray.resources.forEach {
            map[it] = MediaPlayer.create(context, it)
        }
        map
    }

    fun play(@RawRes resourceId: Int) {
        map[resourceId]?.let {
            if (it.isPlaying) {
                it.seekTo(0)
            }
            else {
                it.start()
                it.seekTo(0)
            }
        }
    }

    fun stop() {
        map
            .filter { it.value.isPlaying }
            .forEach { it.value.pause() }
    }

    override fun onCleared() {
        super.onCleared()
        map.forEach { it.value.release() }
    }

}