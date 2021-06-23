package com.ambrosio.plainreddit.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.plainreddit.R
import com.ambrosio.plainreddit.model.Comment

class CommentsAdapter(): RecyclerView.Adapter<CommentsAdapter.MyViewHolder>(){

    var items: MutableList<Comment> = mutableListOf()

    fun setUpdatedData(items: ArrayList<Comment>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View, ): RecyclerView.ViewHolder(view){
        private val tvComment: TextView = view.findViewById(R.id.tvComment)


        fun bind(comment: Comment){
            tvComment.text = comment.body
        }



    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clear() {
        this.items = ArrayList()
        notifyDataSetChanged()
    }
}