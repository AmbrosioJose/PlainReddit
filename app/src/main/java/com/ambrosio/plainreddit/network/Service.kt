package com.ambrosio.plainreddit.network

import com.ambrosio.plainreddit.model.CommentResponse
import com.ambrosio.plainreddit.model.RedditPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET(TOP_ENDPOINT)
    suspend fun getTopPosts(
        @Query("after") after: String? = "",
        @Query("limit") count: Int? = 10
    ): RedditPage

    @GET("$COMMENTS{post_id}$JSON")
    suspend fun getComments(
        @Path("post_id") postId: String,
        @Query("after") after: String? = "",
        @Query("limit") count: Int? = 10,
    ): ArrayList<CommentResponse>
}