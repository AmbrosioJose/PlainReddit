package com.ambrosio.plainreddit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.plainreddit.model.Post
import com.ambrosio.plainreddit.model.RedditPage
import com.ambrosio.plainreddit.network.RetroInstance
import com.ambrosio.plainreddit.network.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private var lastPostWrapperName = ""

    private var postsLiveData: MutableLiveData<ArrayList<Post>> = MutableLiveData()

    fun getPostsListObserver(): MutableLiveData<ArrayList<Post>> {
        return postsLiveData
    }

    fun fetchTopPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(Service::class.java)
            try {

                val redditPage: RedditPage  = retroInstance.getTopPosts(after = lastPostWrapperName)
                lastPostWrapperName = redditPage.data.after
                val posts: MutableList<Post> = mutableListOf()
                redditPage.data.children.forEachIndexed { index, postWrapper ->
                    posts.add(postWrapper.data)
                }
                postsLiveData.postValue(posts as ArrayList<Post>)
            } catch (e: Exception){
                println(e.toString())
            }


        }
    }

}