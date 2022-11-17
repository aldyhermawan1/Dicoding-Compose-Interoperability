package com.dicoding.newsapp.ui.detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.ui.ViewModelFactory

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var newsDetail: NewsEntity
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: NewsDetailViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsDetail = intent.getParcelableExtra<NewsEntity>(NEWS_DATA) as NewsEntity

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NewsDetailScreen(newsDetail = newsDetail, viewModel = viewModel)
                }
            }
        }
    }

    companion object {

        const val NEWS_DATA = "data"
    }
}