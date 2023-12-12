package com.example.uasmobiledana

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val receivedData=if (Build.VERSION.SDK_INT>=33){
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Video>("shadow")
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Video>("shadow")
        }
        val gambar:ImageView=findViewById(R.id.img_gambar)
        val judul:TextView=findViewById(R.id.tv_Judul)
        val description:TextView=findViewById(R.id.tv_video_description)
        val playButton:ImageView=findViewById(R.id.tombol_play)
        if (receivedData!=null){
            gambar.setImageResource(receivedData.gambar)
            judul.text=receivedData.judul
            description.text=receivedData.data_deskripsi
            playButton.setOnClickListener{
                val videoIntent= Intent(this,VideoActivity::class.java)
                videoIntent.putExtra("videoId",receivedData.videoId)
                startActivity(videoIntent)
            }
        }
    }
}