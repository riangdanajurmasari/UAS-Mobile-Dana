package com.example.uasmobiledana

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter (private val context: Context, private val videolist:List<Video>):RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var mediaPlayer: MediaPlayer? = null

    init {

        mediaPlayer = MediaPlayer.create(context, R.raw.musik)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val gambar:ImageView=itemview.findViewById(R.id.iv_video_thumbnail)
        val judulvideo:TextView=itemview.findViewById(R.id.tv_video_title)
        val deskripsi:TextView=itemview.findViewById(R.id.tv_video_description)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (gambar, judul, deskripsi) = videolist[position]
        holder.gambar.setImageResource(gambar)
        holder.judulvideo.text = judul
        holder.deskripsi.text = deskripsi

        holder.itemView.setOnClickListener {
            mediaPlayer?.start()

            onItemClickCallback.onItemClicked(videolist[holder.adapterPosition])

            val audioName = videolist[holder.adapterPosition]
            val audioResourceId = holder.itemView.context.resources.getIdentifier(audioName.sound, "raw", holder.itemView.context.packageName)

            mediaPlayer?.apply {
                reset()
                val context = holder.itemView.context
                val fd = context.resources.openRawResourceFd(audioResourceId)
                setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
                fd.close()
                prepare()
                start()
            }
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Video)
    }

    override fun getItemCount(): Int = videolist.size
}