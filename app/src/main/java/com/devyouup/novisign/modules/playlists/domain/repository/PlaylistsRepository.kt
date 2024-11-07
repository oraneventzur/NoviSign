package com.devyouup.novisign.modules.playlists.domain.repository

import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsRequest
import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsResponse

interface PlaylistsRepository {
    suspend fun getPlaylists(
        getPlaylistsRequest: GetPlaylistsRequest
    ): GetPlaylistsResponse
}