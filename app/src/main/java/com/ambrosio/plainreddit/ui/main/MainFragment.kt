package com.ambrosio.plainreddit.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.plainreddit.R
import com.ambrosio.plainreddit.model.Post
import com.ambrosio.plainreddit.util.PaginationScrollListener

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var postAdapter: PostAdapter
    lateinit var touchActionDelegate: TouchActionDelegate

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun onAttach(context: Context){
        super.onAttach(context)
        context.let{
            if(context is TouchActionDelegate){
                touchActionDelegate = context
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.main_fragment, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getPostsListObserver().observe(viewLifecycleOwner, { posts ->
            print("new posts")
            isLoading = false
            postAdapter.setUpdatedData(posts)
        })

        viewModel.fetchTopPosts()
    }

    private fun initView(view: View) {
        val postsRV = view.findViewById<RecyclerView>(R.id.rvPosts)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        postsRV.layoutManager = layoutManager

        postAdapter = PostAdapter(touchActionDelegate = touchActionDelegate,)
        postsRV.adapter = postAdapter

        postsRV.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true

                viewModel.fetchTopPosts()
            }
        })
    }

    interface TouchActionDelegate{
        fun onPostTap(post: Post)
    }
}

