package com.devyouup.novisign.modules.playlists.domain.model

data class PlaylistItem(
    val collectStatistics: Boolean,
    val creativeKey: String,
    val creativeLabel: String,
    val creativeRefKey: String?,
    val duration: Int,
    val eventTypesList: List<Any>,
    val expireDate: String,
    val orderKey: Int,
    val playlistKey: String,
    val slidePriority: Int,
    val startDate: String
)