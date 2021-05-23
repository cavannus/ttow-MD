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
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "golongan")
    var golongan: String,

    @ColumnInfo(name = "ayah")
    var ayah: String,

    @ColumnInfo(name = "ibu")
    var ibu: String,

    @ColumnInfo(name = "anak")
    var anak: String,

    @ColumnInfo(name = "image_url")
    var image_url: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
