package com.example.news

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter

    private val apiKey = "pub_35336f4e5613c352c4505bd7a08f77a1862fa"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsAdapter = NewsAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter

        val buttonSearch: Button = findViewById(R.id.buttonSearch)
        val editTextQuery: EditText = findViewById(R.id.editTextQuery)

        buttonSearch.setOnClickListener {
            val query = editTextQuery.text.toString()
            if (query.isNotEmpty()) {
                searchNews(query)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun searchNews(query: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = NewsApiUtils.createRequest().getNews(apiKey, query)
                if (response.status == "success") {
                    val articles = response.results
                    if (!articles.isNullOrEmpty()) {
                        newsAdapter.setData(articles)
                    }
                }
            } catch (e: Exception) {
                Log.d("FAIL", e.toString())
            }
        }
    }
}