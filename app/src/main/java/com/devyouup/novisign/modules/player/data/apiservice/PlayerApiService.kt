package com.devyouup.novisign.modules.player.data.apiservice

import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PlayerApiService {
    @GET
    suspend fun getMediaAsset(
        @Url url: String,
    ): GetMediaAssetResponse
}