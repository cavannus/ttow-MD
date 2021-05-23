package id.cavannus.thetaleofwayang.core.data.source.local.room

import androidx.room.*
import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WayangDao {

    @Query("SELECT * FROM wayang")
    fun getAllWayang(): Flow<List<WayangEntity>>

    @Query("SELECT * FROM wayang where isFavorite = 1")
    fun getFavoriteWayang(): Flow<List<WayangEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWayang(wayang: List<WayangEntity>)

    @Update
    fun updateFavoriteWayang(wayang: WayangEntity)
}
