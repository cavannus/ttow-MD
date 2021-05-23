package id.cavannus.thetaleofwayang.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wayang(
    val id: String,
    val nama: String,
    val golongan: String,
    val ayah: String,
    val ibu: String,
    val anak: String,
    val image_url: String,
    val isFavorite: Boolean
) : Parcelable