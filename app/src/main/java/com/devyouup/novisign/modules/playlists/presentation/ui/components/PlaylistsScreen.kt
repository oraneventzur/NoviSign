package com.devyouup.novisign.modules.playlists.presentation.ui.components

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyouup.novisign.modules.playlists.domain.model.Playlist
import com.devyouup.novisign.modules.playlists.presentation.viewmodel.PlaylistsViewModel

@Composable
fun PlaylistsScreen(
    viewModel: PlaylistsViewModel = hiltViewModel(),
    onPlaylistClick: (Playlist) -> Unit
) {
    val activity = (LocalContext.current as Activity)
    val newOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    activity.requestedOrientation = newOrientation

    val state = viewModel.state.value
    Scaffold(
        topBar = { AddAppBar() },
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(state.playlists){ playlist ->
                        PlaylistItem(
                            playlist = playlist,
                            onItemClick = onPlaylistClick
                        )
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(width = 40.dp, height = 40.dp)
                )
            }
        }
    )
}