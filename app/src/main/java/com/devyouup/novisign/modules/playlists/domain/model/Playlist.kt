package com.devyouup.novisign.modules.playlists.domain.model

data class Playlist(
    val channelTime: Int,
    val playlistItems: List<PlaylistItem>,
    val playlistKey: String
)
