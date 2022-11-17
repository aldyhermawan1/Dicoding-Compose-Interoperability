package com.dicoding.newsapp.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.databinding.ItemNewsBinding
import com.dicoding.newsapp.ui.list.NewsAdapter.MyViewHolder

class NewsAdapter(private val onItemClick: (NewsEntity) -> Unit) : ListAdapter<NewsEntity, MyViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class MyViewHolder(private val binding: ItemNewsBinding, val onItemClick: (NewsEntity) -> Unit) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(news: NewsEntity) {
            binding.composeView.setContent {
                MaterialTheme {
                    NewsItem(
                        urlToImage = news.urlToImage,
                        title = news.title,
                        publishedAt = news.publishedAt,
                        onItemClick = { onItemClick(news) }
                    )
                }
            }
        }
    }

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<NewsEntity> =
            object : DiffUtil.ItemCallback<NewsEntity>() {
                override fun areItemsTheSame(oldUser: NewsEntity, newUser: NewsEntity): Boolean {
                    return oldUser.title == newUser.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: NewsEntity, newUser: NewsEntity): Boolean {
                    return oldUser == newUser
                }
            }
    }
}