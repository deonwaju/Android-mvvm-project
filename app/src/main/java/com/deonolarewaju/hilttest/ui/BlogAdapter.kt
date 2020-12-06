package com.deonolarewaju.hilttest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deonolarewaju.hilttest.R
import com.deonolarewaju.hilttest.model.BlogModel
import kotlinx.android.synthetic.main.content_item.view.*

class BlogAdapter(private val list: List<BlogModel>) :
    RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BlogAdapter.ViewHolder, position: Int) {
        holder.itemView.tv_name.text = list[position].title
        holder.itemView.tv_body.text = list[position].body
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}