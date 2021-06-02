package id.cavannus.thetaleofwayang.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.cavannus.thetaleofwayang.core.data.source.local.entity.StoriesEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity

@Database(entities = [WayangEntity::class, StoriesEntity::class], version = 1, exportSchema = false)
abstract class WayangDatabase : RoomDatabase() {

    abstract fun wayangDao(): WayangDao

}