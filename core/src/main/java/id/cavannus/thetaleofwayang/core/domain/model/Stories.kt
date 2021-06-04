package id.cavannus.thetaleofwayang.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stories(
    val id_cerita: String,
    val judul: String,
    val cerita: String,
    val sumber: String,
    val tokoh: String
) : Parcelable