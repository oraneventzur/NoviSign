package com.devyouup.novisign.modules.playlists.data.remote
import com.devyouup.novisign.modules.playlists.domain.model.Playlist

data class GetPlaylistsResponse(
    val breakpointInterval: Int,
    val company: String,
    val modified: Long,
    val playlists: List<Playlist>,
    val screenKey: String
)
