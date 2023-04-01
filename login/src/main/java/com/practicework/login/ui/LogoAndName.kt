package com.practicework.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicework.login.R

@Composable
internal fun LogoAndName() {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(),
            painter = if (MaterialTheme.colors.isLight)
                painterResource(R.drawable.logo_icon_light)
            else painterResource(R.drawable.logo_icon_dark),
            contentDescription = "logo",
            contentScale = ContentScale.FillHeight
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 50.sp,
            text = "GitMit",
        )

    }
}