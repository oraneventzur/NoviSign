package com.devyouup.novisign.modules.player.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.devyouup.novisign.modules.core.data.remote.BaseResponse
import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetRequest
import com.devyouup.novisign.modules.player.domain.usecase.GetMediaAssetUseCase
import com.devyouup.novisign.modules.playlists.domain.model.Playlist
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = PlayerViewModel.PlayerViewModelFactory::class)
class PlayerViewModel @AssistedInject constructor(
    private val getMediaAsset: GetMediaAssetUseCase,
    @Assisted private val playlist: Playlist,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface PlayerViewModelFactory {
        fun create(playlist: Playlist): PlayerViewModel
    }

    private val _playerState = MutableStateFlow<ExoPlayer?>(null)
    val playerState: StateFlow<ExoPlayer?> = _playerState

    private var currentPosition: Long = 0L
    private val mediaItems = initMediaAssets()
    private var index = 1


    fun initializeSlideShow(currentIndex: (Int) -> Unit) {
        viewModelScope.launch {
            val delayTime = (playlist.playlistItems[index].duration.toLong() * 1000L)
            while (index <= playlist.playlistItems.size -1){
                delay(delayTime)
                currentIndex(index++)
            }
        }
    }

    fun initializePlayer(context: Context){
        if (_playerState.value == null){
            viewModelScope.launch {
                val exoPlayer = ExoPlayer.Builder(context).build().also {
                    it.setMediaItems(mediaItems)
                    it.prepare()
                    it.playWhenReady = true
                    it.seekTo(currentPosition)
                    it.addListener(object : Player.Listener {
                        override fun onPlayerError(error: PlaybackException) {
                            handleError(error)
                        }
                    })
                }
                _playerState.value = exoPlayer
            }
        }
    }


    private fun initMediaAssets(): ArrayList<MediaItem> {
        val mediaItems = ArrayList<MediaItem>()
        for (playlistItem in playlist.playlistItems) {
            getMediaAsset(GetMediaAssetRequest(playlistItem)).onEach { result ->
                when (result) {
                    is BaseResponse.Success -> {
                        mediaItems.add(result.value.mediaItem)
                    }

                    is BaseResponse.Failure -> {
                        println(result.message)
                    }

                    else -> {}
                }

            }.launchIn(viewModelScope)
        }
        return mediaItems
    }

    fun savePlayerState() {
        _playerState.value?.let {
            currentPosition = it.currentPosition
        }
    }

    fun releasePlayer() {
        _playerState.value?.release()
        _playerState.value = null
    }

    private fun handleError(error: PlaybackException) {
        when (error.errorCode) {
            PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> {
                // Handle network connection error
                println("Network connection error")
            }

            PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND -> {
                // Handle file not found error
                println("File not found")
            }

            PlaybackException.ERROR_CODE_DECODER_INIT_FAILED -> {
                // Handle decoder initialization error
                println("Decoder initialization error")
            }

            else -> {
                // Handle other types of errors
                println("Other error: ${error.message}")
            }
        }
    }


}


