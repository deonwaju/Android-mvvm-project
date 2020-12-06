package com.deonolarewaju.hilttest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.deonolarewaju.hilttest.R
import com.deonolarewaju.hilttest.model.BlogModel
import com.deonolarewaju.hilttest.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebugger"

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        subscribeObservers()
        init()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<BlogModel>> -> {
                    displayProgressBar(false)
//                    appendBlogTitles(dataState.data)
                    setDataToRecyclerView(dataState.data)

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

//    private fun loadData() {
//        viewModel.
////        viewModel.loadDataFromWeb().observe(this, Observer {
////            setDataToRecyclerView(it)
////        })
//    }

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

    private fun init() {
        setDataToRecyclerView(listOf())
        subscribeObservers()

    }

    private fun loadData() {

    }

    private fun setDataToRecyclerView(userList: List<BlogModel>) {
        recyclerViewMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BlogAdapter(userList)
        }
    }
}