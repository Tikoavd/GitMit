package com.practicework.repos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practicework.repos.domain.models.Repo


@Composable
internal fun RepoCard(repo: Repo) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 6.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize(),
        ) {

            Text(
                modifier = Modifier.padding(4.dp),
                text = repo.name
            )

            Card(
                modifier = Modifier.wrapContentWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 6.dp,
                        vertical = 4.dp
                    ),
                    text = repo.visibility
                )
            }


            Text(
                modifier = Modifier.padding(4.dp),
                text = "Language: ${repo.language}"
            )
        }
    }
}