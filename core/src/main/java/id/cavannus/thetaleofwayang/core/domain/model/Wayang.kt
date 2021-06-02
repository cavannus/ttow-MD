package id.cavannus.thetaleofwayang.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wayang(
    val id_wayang: String,
    val nm_wayang: String,
    val watak_wayang: String,
    val isFavorite: Boolean
) : Parcelable