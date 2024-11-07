package com.devyouup.novisign.modules.playlists.data.apiservice

import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PlaylistsApiService {
    @GET
    suspend fun getSharedLists(
        @Url url: String,
    ): GetPlaylistsResponse
}