package com.devyouup.novisign.modules.player.domain.repository

import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetRequest
import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetResponse


interface PlayerRepository {
    suspend fun getMediaAsset(
        getPlaylistsRequest: GetMediaAssetRequest
    ): GetMediaAssetResponse
}