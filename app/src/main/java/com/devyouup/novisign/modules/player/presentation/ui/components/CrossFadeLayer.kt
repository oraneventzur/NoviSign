package com.devyouup.novisign.modules.player.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CrossFadeLayer(){
    Box(
        modifier = Modifier.fillMaxHeight()
            .fillMaxWidth().background(color = Color.Black)
    ){
        Text("Overlay is here")
    }
}