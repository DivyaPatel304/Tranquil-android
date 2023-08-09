package com.mdev.tranquil


data class YouTubeSearchResponse(
    val items: List<YouTubeVideoItem>
)

data class YouTubeVideoItem(
    val id: YouTubeVideoId,
    val snippet: YouTubeVideoSnippet
)

data class YouTubeVideoId(
    val videoId: String
)

data class YouTubeVideoSnippet(
    val title: String,
    var description: String,
    val thumbnails: YouTubeVideoThumbnails
)

data class YouTubeVideoThumbnails(
    val medium: YouTubeThumbnail,
    val high: YouTubeThumbnail
)

data class YouTubeThumbnail(
    val url: String
)
