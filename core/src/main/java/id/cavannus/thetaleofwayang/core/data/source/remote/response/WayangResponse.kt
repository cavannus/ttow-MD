package id.cavannus.thetaleofwayang.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class WayangResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("golongan")
    val golongan: String,

    @field:SerializedName("ayah")
    val ayah: String,

    @field:SerializedName("ibu")
    val ibu: String,

    @field:SerializedName("anak")
    val anak: String,

    @field:SerializedName("image_url")
    val image_url: String
)
