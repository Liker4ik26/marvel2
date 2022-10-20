package com.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun HomeScreen() {
    HomeScreen(d = "")
}

@Composable
private fun HomeScreen(d: String) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = com.core_ui.R.drawable.logo_marvel),
                contentDescription = stringResource(com.core_ui.R.string.description_logo_marvel_image),
                Modifier.size(256.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

    }
}

@Preview
@Composable
fun HomeScreenPresentation() {
    HomeScreen()
}