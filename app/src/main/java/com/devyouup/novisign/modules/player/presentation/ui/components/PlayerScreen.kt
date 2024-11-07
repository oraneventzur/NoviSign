package com.devyouup.novisign.modules.player.presentation.ui.components

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import coil.compose.AsyncImage
import com.devyouup.novisign.R
import com.devyouup.novisign.modules.core.utils.BASE_URL
import com.devyouup.novisign.modules.core.utils.GET_MEDIA_ASSET
import com.devyouup.novisign.modules.player.domain.model.MediaItemType
import com.devyouup.novisign.modules.player.presentation.viewmodel.PlayerViewModel
import com.devyouup.novisign.modules.playlists.domain.model.Playlist
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@Composable
fun PlayerScreen(
    playlist: Playlist,
    onBackNavigationClick: () -> Unit
) {
    val viewModel = hiltViewModel<PlayerViewModel, PlayerViewModel.PlayerViewModelFactory> { factory ->
        factory.create(playlist)
    }
    val newOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    (LocalContext.current as Activity).requestedOrientation = newOrientation

    val context = LocalContext.current
    val player by viewModel.playerState.collectAsState()

    //var currentIndex by remember { mutableStateOf(0) }
   // var currentMedia = viewModel.mediaItems[currentIndex]


    LaunchedEffect(playlist) {
        viewModel.initializePlayer(context)
//        viewModel.initializeSlideShow { index: Int ->
//            currentIndex = index
//        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.savePlayerState()
            viewModel.releasePlayer()
        }
    }

//    Crossfade(targetState = currentMedia, label = "") { media: MediaItem ->
//        when (media.mediaId) {
//            MediaItemType.Image.toString() -> {
//                AsyncImage(
//                    model = (BASE_URL + GET_MEDIA_ASSET + playlist.playlistItems[currentIndex].creativeKey),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
//                )
//            }
//            MediaItemType.Video.toString() -> {
//                viewModel.initializePlayer(LocalContext.current, media)
//                Media3AndroidView(player)
//                //PlayerControls(player)
//            }
//            else -> {}
//        }
//    }

    Column {
        Media3AndroidView(player)
        PlayerControls(player)
    }
}

