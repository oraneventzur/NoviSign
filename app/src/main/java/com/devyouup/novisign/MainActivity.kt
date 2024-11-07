package com.devyouup.novisign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devyouup.novisign.modules.core.navigation.Screen
import com.devyouup.novisign.modules.player.presentation.ui.components.PlayerScreen
import com.devyouup.novisign.modules.player.presentation.viewmodel.PlayerViewModel
import com.devyouup.novisign.modules.playlists.domain.model.Playlist
import com.devyouup.novisign.modules.playlists.presentation.ui.components.PlaylistsScreen
import com.devyouup.novisign.ui.theme.NoviSignTheme
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoviSignTheme {
                Scaffold(
                    topBar = {
                        title = stringResource(R.string.app_name)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PlaylistsScreen.route
                    ) {

                        /*
                        *  In case of a simple and small project with no extra complexity would have use
                        *  a shared view model to hold data for both screens over this approach of passing
                        *  objects between composable through json parsing.
                        *  */

                        composable(Screen.PlaylistsScreen.route) {
                            PlaylistsScreen { playlist: Playlist ->
                                navController.navigate(
                                    Screen.PlayerScreen.route.replace(
                                        oldValue = "{playlist}",
                                        newValue = GsonBuilder().create().toJson(playlist)
                                    )
                                )
                            }
                        }
                        composable(Screen.PlayerScreen.route) { backStackEntry ->
                            val playlist = Gson().fromJson(
                                backStackEntry.arguments?.getString("playlist"), Playlist::class.java)
                            PlayerScreen(playlist = playlist) {
                                navController.navigate(Screen.PlaylistsScreen.route)
                            }
                        }
                    }
                }
            }
        }
    }
}
