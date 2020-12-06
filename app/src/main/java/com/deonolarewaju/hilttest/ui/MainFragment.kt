package com.deonolarewaju.hilttest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.deonolarewaju.hilttest.R
import com.deonolarewaju.hilttest.model.BlogModel
import com.deonolarewaju.hilttest.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder
import java.util.*

@AndroidEntryPoint
class MainFragment constructor(
    private val someString: String
//    private val someObject: Object,
//    private val mainRepository: MainRepository
) : Fragment(R.layout.fragment_main) {
    private val TAG = "MainFragment"


    private val viewModel: MainViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ${someString}")

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<BlogModel>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)

                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)

                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            textViewStart.text = message
        } else {
            textViewStart.text = "Unknown error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<BlogModel>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        textViewStart.text = sb.toString()
    }

}