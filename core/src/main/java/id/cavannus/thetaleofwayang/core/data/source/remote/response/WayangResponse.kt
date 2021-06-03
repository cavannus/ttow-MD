package id.cavannus.thetaleofwayang.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class WayangResponse(
    @field:SerializedName("id_wayang")
    val id_wayang: String,

    @field:SerializedName("nm_wayang")
    val nm_wayang: String,

    @field:SerializedName("watak_wayang")
    val watak_wayang: String,

    @field:SerializedName("foto")
    val foto: String
)
