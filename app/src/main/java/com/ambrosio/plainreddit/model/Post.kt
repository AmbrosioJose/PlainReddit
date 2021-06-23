package com.ambrosio.plainreddit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Post (
    val id: String,
    val title: String,
    val selfText: String? = "",
    val name: String?= "",
    @SerialName("subreddit_name_prefixed")
    val subreddit_name_prefixed: String? = "",
    val author: String,
    val thumbnail: String? = "",
    @SerialName("post_hint")
    val post_hint: String? = "",
    @SerialName("subreddit_subscribers")
    val subredditSubscribers: Int
    )

@Serializable
class PostWrapper(
    val kind: String,
    @SerialName("data")
    val data: Post
)

@Serializable
class PageData(
    val after: String,
    val dist: Int,
    val modhash: String,
    @SerialName("children")
    var children: ArrayList<PostWrapper>,

)
@Serializable
class RedditPage(
  val data: PageData
)
