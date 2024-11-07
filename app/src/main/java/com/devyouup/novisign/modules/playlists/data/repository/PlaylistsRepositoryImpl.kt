package com.devyouup.novisign.modules.playlists.data.repository

import com.devyouup.novisign.modules.playlists.data.apiservice.PlaylistsApiService
import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsRequest
import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsResponse
import com.devyouup.novisign.modules.playlists.domain.repository.PlaylistsRepository
import com.devyouup.novisign.modules.core.utils.GET_PLAYLISTS_ITEMS
import javax.inject.Inject

class PlaylistsRepositoryImpl @Inject constructor(
    private val apiService: PlaylistsApiService
): PlaylistsRepository {
    override suspend fun getPlaylists(
        getPlaylistsRequest: GetPlaylistsRequest
    ): GetPlaylistsResponse {
        return apiService.getSharedLists(
            url = GET_PLAYLISTS_ITEMS + getPlaylistsRequest.screenKeyUUID
        )
    }
}