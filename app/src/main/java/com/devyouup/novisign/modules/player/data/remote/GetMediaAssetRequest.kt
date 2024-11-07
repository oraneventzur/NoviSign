package com.devyouup.novisign.modules.player.data.remote

import com.devyouup.novisign.modules.playlists.domain.model.PlaylistItem

data class GetMediaAssetRequest(
    val playlistItem: PlaylistItem
)