package com.devyouup.novisign.modules.core.navigation

sealed class Screen(val route: String) {
    data object PlaylistsScreen: Screen("PlaylistsScreen")
    data object PlayerScreen: Screen("PlayerScreen/{playlist}")

}