package com.devyouup.novisign.modules.playlists.presentation.viewmodel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyouup.novisign.modules.core.data.remote.BaseResponse
import com.devyouup.novisign.modules.core.utils.SCREEN_KEY_UUID
import com.devyouup.novisign.modules.playlists.data.remote.GetPlaylistsRequest
import com.devyouup.novisign.modules.playlists.domain.state.PlayListsState
import com.devyouup.novisign.modules.playlists.domain.usecase.GetPlaylistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(PlayListsState())
    val state: State<PlayListsState> = _state

    init {
        getPlaylists()
    }

    private fun getPlaylists(){
        val request = GetPlaylistsRequest(
            screenKeyUUID = SCREEN_KEY_UUID
        )
        getPlaylistsUseCase(request = request).onEach { result ->
            when(result) {
                is BaseResponse.Success -> {
                    _state.value = PlayListsState(playlists = result.value.playlists ?: emptyList())
                }
                is BaseResponse.Failure -> {
                    _state.value = PlayListsState(error = result.message ?: "An unexpected error accrued")
                }
                is BaseResponse.Loading -> {
                    _state.value = PlayListsState(isLoading = true)
                }
                is BaseResponse.NetworkError -> {
                    _state.value = PlayListsState(error = "Network who's? please check your network and try again")
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

}