package com.dicoding.newsapp.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.newsapp.R
import com.dicoding.newsapp.data.local.entity.NewsEntity

@Composable
fun NewsDetailScreen(
    newsDetail: NewsEntity,
    viewModel: NewsDetailViewModel
) {
    viewModel.setNewsData(newsDetail)
    val bookmarkStatus by viewModel.bookmarkStatus.observeAsState(initial = false)
    NewsDetailContent(
        title = newsDetail.title,
        url = newsDetail.url.toString(),
        bookmarkStatus = bookmarkStatus,
        updateBookmarkStatus = {
            viewModel.changeBookmark(newsDetail)
        }
    )
}

@Composable
fun NewsDetailContent(
    title: String,
    url: String,
    bookmarkStatus: Boolean,
    updateBookmarkStatus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = updateBookmarkStatus) {
                        Icon(
                            painter = if (bookmarkStatus) {
                                painterResource(id = R.drawable.ic_bookmarked_white)
                            } else {
                                painterResource(id = R.drawable.ic_bookmark_white)
                            },
                            contentDescription = stringResource(id = R.string.save_bookmark)
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsDetailContentPreview() {
    MaterialTheme {
        NewsDetailContent(title = "New News", url = "www.dicoding.com", bookmarkStatus = false, updateBookmarkStatus = { })
    }
}