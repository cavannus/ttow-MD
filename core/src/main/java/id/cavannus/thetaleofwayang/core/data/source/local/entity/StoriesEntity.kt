package id.cavannus.thetaleofwayang.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "stories")
data class StoriesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_cerita")
    var id_cerita: String,

    @ColumnInfo(name = "judul")
    var judul: String,

    @ColumnInfo(name = "cerita")
    var cerita: String,

    @ColumnInfo(name = "sumber")
    var sumber: String,

    @ColumnInfo(name = "tokoh")
    var tokoh: String
) : Parcelable
