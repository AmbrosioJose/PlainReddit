package com.ambrosio.plainreddit.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.plainreddit.model.Comment
import com.ambrosio.plainreddit.model.CommentResponse
import com.ambrosio.plainreddit.network.RetroInstance
import com.ambrosio.plainreddit.network.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CommentsViewModel : ViewModel() {
    private var commentsLiveData: MutableLiveData<ArrayList<Comment>> = MutableLiveData()

    fun getCommentsListObserver(): MutableLiveData<ArrayList<Comment>> {
        return commentsLiveData
    }

    fun fetchComments(postId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(Service::class.java)
            try {

                val commentResponses: ArrayList<CommentResponse> = retroInstance.getComments(postId = postId)
                val comments: MutableList<Comment> = mutableListOf()
                commentResponses.forEach {commentResponse ->
                    commentResponse.data.children.forEachIndexed { index, commentWrapper ->
                        comments.add(commentWrapper.data)
                    }
                }
                commentsLiveData.postValue(comments as ArrayList<Comment>)
            } catch (e: Exception){
                println(e.toString())
            }


        }
    }
}