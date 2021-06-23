package com.ambrosio.plainreddit.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.plainreddit.R
import com.ambrosio.plainreddit.model.Post
import com.bumptech.glide.Glide

class PostAdapter(private val touchActionDelegate: MainFragment.TouchActionDelegate? ): RecyclerView.Adapter<PostAdapter.MyViewHolder>(){

    var items: MutableList<Post> = mutableListOf()

    fun setUpdatedData(items: ArrayList<Post>){
        print("new times!! ${items.size}")
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View, touchActionDelegate: MainFragment.TouchActionDelegate?,): RecyclerView.ViewHolder(view){
        private val imgThumbnail: ImageView = view.findViewById(R.id.imgThumbnail)
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvMessage: TextView = view.findViewById(R.id.tvMessage)

        fun bind(post: Post, touchActionDelegate: MainFragment.TouchActionDelegate?, holder: MyViewHolder){
            tvTitle.text = post.title
            tvMessage.text = post.subreddit_name_prefixed



            if(!post.thumbnail.isNullOrBlank() && post.thumbnail.contains("http")){
                Glide.with(imgThumbnail.context)
                        .load(post.thumbnail)
                        .into(imgThumbnail)
            }

            holder.itemView.setOnClickListener(){
                touchActionDelegate?.onPostTap(post)
            }
        }



    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return MyViewHolder(view, touchActionDelegate)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        holder.bind(items[position], this.touchActionDelegate, holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clear() {
        this.items = ArrayList()
        notifyDataSetChanged()
    }
}