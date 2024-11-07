package com.devyouup.novisign.modules.player.data.repository

import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import com.devyouup.novisign.modules.core.utils.BASE_URL
import com.devyouup.novisign.modules.core.utils.DURATION_TIME_MULTIPLIER
import com.devyouup.novisign.modules.core.utils.GET_MEDIA_ASSET
import com.devyouup.novisign.modules.player.data.apiservice.PlayerApiService
import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetRequest
import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetResponse
import com.devyouup.novisign.modules.player.domain.model.MediaItemType
import com.devyouup.novisign.modules.player.domain.repository.PlayerRepository
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val apiService: PlayerApiService
): PlayerRepository {

    @OptIn(UnstableApi::class)
    override suspend fun getMediaAsset(
        getPlaylistsRequest: GetMediaAssetRequest
    ): GetMediaAssetResponse {
        val type = getMimeType(getPlaylistsRequest.playlistItem.creativeKey)
        val mediaItem: MediaItem = when(type){
            MediaItemType.Image -> {
                MediaItem.Builder()
                    .setUri(BASE_URL + GET_MEDIA_ASSET + getPlaylistsRequest.playlistItem.creativeKey)
                    .setTag(getPlaylistsRequest.playlistItem)
                    .setMediaId(type.toString())
                    .setImageDurationMs(getPlaylistsRequest.playlistItem.duration.toLong() * DURATION_TIME_MULTIPLIER)
                    .build()
            }

            MediaItemType.Video -> {
                MediaItem.Builder()
                    .setUri(BASE_URL + GET_MEDIA_ASSET + getPlaylistsRequest.playlistItem.creativeKey)
                    .setTag(getPlaylistsRequest.playlistItem)
                    .setMediaId(type.toString())
                    .build()
            }
        }
        return GetMediaAssetResponse(
            mediaItem
        )
    }

    private fun getMimeType(filePath: String) : MediaItemType {
        val type = filePath.split(".")[1]
        return if (type.endsWith("jpg")||type.endsWith("png")
            ||type.endsWith("jpeg")||type.endsWith("gif")){
            MediaItemType.Image
        }else{
            MediaItemType.Video
        }
    }
}