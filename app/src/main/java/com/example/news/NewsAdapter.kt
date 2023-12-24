package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = mutableListOf<Article>()

    fun setData(news: List<Article>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.bind(newsItem)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private var descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(article: Article) {
            titleTextView.text = article.title
            descriptionTextView.text = article.description
        }
    }
}