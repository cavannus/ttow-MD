package id.cavannus.thetaleofwayang.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListStoriesResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("result")
    val story: List<StoriesResponse>
)