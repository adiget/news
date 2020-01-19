package com.ags.annada.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ags.annada.newsapp.R
import com.ags.annada.newsapp.databinding.ItemNewsBinding
import com.ags.annada.newsapp.room.entities.Content
import com.ags.annada.newsapp.viewmodel.NewsViewModel

class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private lateinit var contents: List<Content>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.bind(contents[position])
    }

    fun setContents(contents: List<Content>) {
        this.contents = contents
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (::contents.isInitialized) contents.size else 0
    }


    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = NewsViewModel()

        fun bind(content: Content) {
            viewModel.bind(content)
            binding.viewModel = viewModel
        }
    }
}