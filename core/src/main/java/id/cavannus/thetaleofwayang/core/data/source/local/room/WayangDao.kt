package id.cavannus.thetaleofwayang.core.data.source.local.room

import androidx.room.*
import id.cavannus.thetaleofwayang.core.data.source.local.entity.SearchWayangEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.StoriesEntity
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

    @Query("SELECT * FROM stories")
    fun getAllStories(): Flow<List<StoriesEntity>>

    @Query("SELECT * FROM stories WHERE id_cerita = :id")
    fun getStoryById(id: String): Flow<StoriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<StoriesEntity>)

    @Update
    fun updateFavoriteWayang(wayang: WayangEntity)

    @Query("SELECT * FROM searchWayang WHERE nm_wayang LIKE :query")
    fun searchWayang(query: String): Flow<List<SearchWayangEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchWayang(wayangList: List<SearchWayangEntity>)
}
