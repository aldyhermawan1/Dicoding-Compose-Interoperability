package com.dicoding.newsapp.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage

@Composable
fun NewsItem(
    urlToImage: String?,
    title: String,
    publishedAt: String,
    onItemClick: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.clickable { onItemClick() }) {
        val (posterImage, titleText, publishedDateText) = createRefs()

        AsyncImage(
            model = urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .constrainAs(posterImage) {
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            maxLines = 3,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(titleText) {
                start.linkTo(posterImage.end, margin = 16.dp)
                top.linkTo(parent.top, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = publishedAt,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(publishedDateText) {
                top.linkTo(titleText.bottom, margin = 8.dp)
                start.linkTo(titleText.start)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    MaterialTheme {
        NewsItem(urlToImage = "", title = "New News", publishedAt = "2022-02-22") {

        }
    }
}