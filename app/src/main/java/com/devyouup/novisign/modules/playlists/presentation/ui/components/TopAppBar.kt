package com.devyouup.novisign.modules.playlists.presentation.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.devyouup.novisign.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) }
    )
}