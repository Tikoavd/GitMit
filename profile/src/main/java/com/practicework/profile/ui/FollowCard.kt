package com.practicework.profile.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
internal fun FollowCard(
    text: String,
    count: Int,
    color: Color
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp,
        backgroundColor = color
    ) {
        Text(
            modifier = Modifier.padding(2.dp),
            text = "$text $count",
            color = Color.White
        )
    }
}