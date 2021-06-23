package com.ambrosio.plainreddit.network


const val BASE_URL = "https://www.reddit.com/"
const val TOP_SUBREDDIT = "r/all/top"
const val COMMENTS = "comments/"
const val JSON = ".json"
const val TOP_ENDPOINT = TOP_SUBREDDIT + JSON
fun buildEndpoint(subreddit: String): String {
    return "$subreddit$JSON"
}