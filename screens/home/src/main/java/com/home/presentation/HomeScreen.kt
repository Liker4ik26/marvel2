package com.home.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.home.presentation.components.CardHero
import com.marvel2.home.R
import com.ramcosta.composedestinations.annotation.Destination
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Destination
@Composable
fun HomeScreen() {
    val imageHeroes = listOf(
        R.drawable.deadpool,
        R.drawable.capitan_america,
        R.drawable.iron_man,
        R.drawable.doctor_strange,
        R.drawable.thanos,
        R.drawable.thor,
        R.drawable.spiderman
    )
    val lazyListState = rememberLazyListState()
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = com.core_ui.R.drawable.logo_marvel),
                contentDescription = stringResource(com.core_ui.R.string.description_logo_marvel_image),
                Modifier
                    .width(180.dp)
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        Text(
            modifier = Modifier,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1,
            text = stringResource(R.string.chose_hero)
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(horizontal = 13.dp)
        ) {
            itemsIndexed(
                listOf(
                    R.string.deadpool,
                    R.string.capitan_america,
                    R.string.iron_man,
                    R.string.doctor_strange,
                    R.string.thanos,
                    R.string.thor,
                    R.string.spider_man,
                )
            ) { index, item ->

                CardHero(
                    textHero = item,
                    imageHero = imageHeroes[index],
                    textDesc = item,
                )
            }
        }
    }

}

@Preview
@Composable
fun HomeScreenPresentation() {
    HomeScreen()
}
