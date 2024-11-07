package com.devyouup.novisign.modules.playlists.domain.state

import com.devyouup.novisign.modules.playlists.domain.model.Playlist

data class PlayListsState(
    val isLoading: Boolean = false,
    val playlists: List<Playlist> = emptyList(),
    val error: String = ""
)

