package com.devyouup.novisign.modules.playlists.domain.usecase

import com.devyouup.novisign.modules.core.data.remote.BaseResponse
import com.devyouup.novisign.modules.playlists.data.mappers.toPlaylist
import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsRequest
import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsResponse
import com.devyouup.novisign.modules.playlists.domain.model.Playlist
import com.devyouup.novisign.modules.playlists.domain.repository.PlaylistsRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlaylistsUseCase @Inject constructor(
    private val repository: PlaylistsRepository
) {
    operator fun invoke(
        request: GetPlaylistsRequest
    ): Flow<BaseResponse<GetPlaylistsResponse>> = flow {

        try {
            emit(BaseResponse.Loading)
            val playlistsResponse = repository.getPlaylists(request)
            playlistsResponse.playlists.map { toPlaylist(it) }
            emit(BaseResponse.Success(playlistsResponse))
        }catch (e: HttpException){
            emit(BaseResponse.Failure(
                code = e.code().toString(),
                errorCode = null,
                networkCode = null,
                message = e.message ?: "An unexpected error"
            ))
        }catch (e: IOException){
            emit(BaseResponse.NetworkError)
        }
    }
}