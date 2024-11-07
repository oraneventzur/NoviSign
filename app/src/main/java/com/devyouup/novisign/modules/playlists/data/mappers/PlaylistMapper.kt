package com.devyouup.novisign.modules.playlists.data.mappers

import com.devyouup.novisign.modules.playlists.domain.model.Playlist
import com.devyouup.novisign.modules.playlists.domain.model.PlaylistItem

fun toPlaylist(dataModel: Playlist): Playlist {
    return Playlist(
        channelTime = dataModel.channelTime,
        playlistItems = dataModel.playlistItems.map { toPlaylistItem(it) },
        playlistKey = dataModel.playlistKey
    )
}

fun toPlaylistItem(dataModel: PlaylistItem): PlaylistItem {
    return PlaylistItem(
        collectStatistics = dataModel.collectStatistics,
        creativeKey = dataModel.creativeKey,
        creativeLabel = dataModel.creativeLabel,
        creativeRefKey = dataModel.creativeRefKey,
        duration = dataModel.duration,
        eventTypesList = dataModel.eventTypesList,
        expireDate = dataModel.expireDate,
        orderKey = dataModel.orderKey,
        playlistKey = dataModel.playlistKey,
        slidePriority = dataModel.slidePriority,
        startDate = dataModel.startDate,
    )
}