package com.mdev.tranquil

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.mdev.tranquil.databinding.ItemYoutubeVideoBinding

class YouTubeListAdapter(
    private val context: Context,
    private val videoItems: List<YouTubeVideoItem>
) : RecyclerView.Adapter<YouTubeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemYoutubeVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoItem = videoItems[position]
        val snippet = videoItem.snippet

        holder.binding.videoTitleTextView.text = snippet.title
        holder.binding.videoDescriptionTextView.text = snippet.description
        holder.binding.videoIdTextView.text = videoItem.id.videoId

        val thumbnailUrl = snippet.thumbnails.medium.url
        Picasso.get().load(thumbnailUrl).into(holder.binding.videoThumbnailImageView)

        // Set an OnClickListener to open the YouTube app with the videoId
        holder.binding.root.setOnClickListener {
            val videoId = videoItem.id.videoId
            val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
            youtubeIntent.putExtra("VIDEO_ID", videoId)
            youtubeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            try {
                context.startActivity(youtubeIntent)
            } catch (e: ActivityNotFoundException) {
                // Handle the case where the YouTube app is not installed
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=$videoId")
                )
                context.startActivity(webIntent)
            }
        }
    }
    override fun getItemCount(): Int {
        return videoItems.size
    }

    inner class ViewHolder(val binding: ItemYoutubeVideoBinding) :
        RecyclerView.ViewHolder(binding.root)
}
