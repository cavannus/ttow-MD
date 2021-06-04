package id.cavannus.thetaleofwayang.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StoriesResponse(
    @field:SerializedName("id_cerita")
    val id_cerita: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("cerita")
    val cerita: String,

    @field:SerializedName("sumber")
    val sumber: String,

    @field:SerializedName("tokoh")
    val tokoh: String
)
