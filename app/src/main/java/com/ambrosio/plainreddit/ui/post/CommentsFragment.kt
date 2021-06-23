package com.ambrosio.plainreddit.ui.post

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.plainreddit.R
import com.ambrosio.plainreddit.ui.main.PostAdapter

class CommentsFragment(val postId: String) : Fragment() {

    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var viewModel: CommentsViewModel

    companion object {
        fun newInstance(postId: String) = CommentsFragment(postId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view =  inflater.inflate(R.layout.comments_fragment, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)

        viewModel.getCommentsListObserver().observe(viewLifecycleOwner,{ comments ->
            commentsAdapter.setUpdatedData(comments)
        })

        viewModel.fetchComments(postId)
    }

    private fun initView(view: View){
        val commentsRV = view.findViewById<RecyclerView>(R.id.rvComments)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        commentsRV.layoutManager = layoutManager

        commentsAdapter = CommentsAdapter()
        commentsRV.adapter = commentsAdapter
    }

}