package com.ags.annada.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ags.annada.newsapp.R
import com.ags.annada.newsapp.databinding.NewsFragmentBinding
import com.ags.annada.newsapp.viewmodel.ContentListViewModel
import com.ags.annada.newsapp.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: ContentListViewModel
    private lateinit var binding: NewsFragmentBinding
    private var errorSnackbar: Snackbar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity))
            .get(ContentListViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })


        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false)

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        recyclerView = binding.newsList

        recyclerView.layoutManager = linearLayoutManager

        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        layoutManager = (recyclerView.layoutManager as LinearLayoutManager?)!!

        binding.viewModel = viewModel

        viewModel.allContents.observe(this, Observer { news ->
            // Update the cached copy of the news in the adapter.
            news?.let { viewModel.newsListAdapter.setContents(it)}
        })


        return binding.root
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}