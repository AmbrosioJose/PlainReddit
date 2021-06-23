package com.ambrosio.plainreddit.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Comment (
    val body: String,
)

@Serializable
class CommentWrapper(
    @SerialName("data")
    val data: Comment
)

@Serializable
class ResponseData(
    @SerialName("children")
    var children: ArrayList<CommentWrapper>,

    )

@Serializable
class CommentResponse(
    val data: ResponseData
)