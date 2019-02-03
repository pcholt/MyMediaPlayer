package com.overswell.mymediaplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.lifecycle.ViewModel
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.context = this

        vm.resourceArray.resources.forEach { entry ->
            (layoutInflater.inflate(R.layout.noise_button, null) as TextView)
                .also {
                    (flexbox_layout as FlexboxLayout).addView(it)
                    it.text = entry.labelButton
                }
                .setOnClickListener {
                    vm.play(entry.audioResource)
                }
        }

        stop.setOnClickListener {
            vm.stop()
        }

    }

}

class MainViewModel(val resourceArray: ResourceArray) : ViewModel() {

    lateinit var context: Context

    private val map by lazy {
        HashMap<Int, MediaPlayer>().also { map ->
            resourceArray.resources.forEach {
                map[it.audioResource] = MediaPlayer.create(context, it.audioResource)
            }
        }
    }

    fun play(@RawRes resourceId: Int) {
        map[resourceId]?.let {
            if (it.isPlaying) {
                it.seekTo(0)
            } else {
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
        map
            .forEach { it.value.release() }
    }

}