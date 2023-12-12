package com.example.uasmobiledana

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uasmobiledana.databinding.ActivityListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding

    private lateinit var recyclerView: RecyclerView
    private val list= ArrayList<Video>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        recyclerView=findViewById(R.id.vv_video)
        recyclerView.setHasFixedSize(true)
        list.addAll(getList())
        showRecyclerList()
    }

    private fun getList():ArrayList<Video>{
        val gambar=resources.obtainTypedArray(R.array.video_thumbnail)
        val dataName=resources.getStringArray(R.array.video_title)
        val dataDesripsi=resources.getStringArray(R.array.video_description)
        val videoId=resources.obtainTypedArray(R.array.video_id)
        val dataSoundNames=resources.getStringArray(R.array.data_sound)
        val listvideo=ArrayList<Video>()
        for (i in dataName.indices){
            val video=Video(gambar.getResourceId(i,-1),dataName[i],dataDesripsi[i],videoId.getResourceId(i,-1),dataSoundNames[i])
            listvideo.add(video)
        }
        return listvideo

    }
    private fun showRecyclerList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listadapter = ListAdapter(this, list)
        recyclerView.adapter = listadapter

        listadapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Video) {
                val detailIntent = Intent(this@ListActivity, DetailActivity::class.java)
                detailIntent.putExtra("shadow", data)
                startActivity(detailIntent)
            }
        })
    }
}