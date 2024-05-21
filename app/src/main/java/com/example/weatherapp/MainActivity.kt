package com.example.weatherapp


import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.fragments.DaysFragment
import com.example.weatherapp.fragments.HoursFragment
import com.example.weatherapp.fragments.MainFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        days_hours_btn()
        show_requests()

        mediaPlayer = MediaPlayer.create(this, R.raw.sunny_sound)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeCurrentCard, MainFragment.newInstance())
            .commit()
    }

    override fun onStart() {
        super.onStart()
        sound()
        //rain_sound = MediaPlayer.create(this, R.raw.sunny_sound)
    }

    private fun sound() = with(binding){
        soundBtn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                pauseSong()
            } else {
                playSong()
            }
        }
    }
    private fun days_hours_btn() = with(binding) {
        hoursBtn.setOnClickListener {
            //  soundPlay(sunny_sound)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeForecast, HoursFragment.newInstance())
                .commit()
        }
        daysBtn.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeForecast, DaysFragment.newInstance())
                .commit()
        }
    }

    private fun playSong() {
        mediaPlayer.start()
    }

    private fun pauseSong() {
        mediaPlayer.pause()
    }

    private fun show_requests() = with(binding) {
        showReqBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Requests::class.java)
            startActivity(intent)
        }
    }
}