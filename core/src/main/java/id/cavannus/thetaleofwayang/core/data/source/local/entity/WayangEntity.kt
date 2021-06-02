package id.cavannus.thetaleofwayang.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "wayang")
data class WayangEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_wayang")
    var id_wayang: String,

    @ColumnInfo(name = "nm_wayang")
    var nm_wayang: String,

    @ColumnInfo(name = "watak_wayang")
    var watak_wayang: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
