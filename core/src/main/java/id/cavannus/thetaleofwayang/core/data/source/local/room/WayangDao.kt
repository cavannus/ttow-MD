package id.cavannus.thetaleofwayang.core.data.source.local.room

import androidx.room.*
import id.cavannus.thetaleofwayang.core.data.source.local.entity.FavoriteEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.SearchEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.StoriesEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface WayangDao {
    //HISTORY
    @Query("SELECT * FROM wayang_tb")
    fun getAllWayang(): Flow<List<WayangEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWayang(wayang: List<WayangEntity>)

    @Query("SELECT * FROM wayang_tb WHERE nm_wayang LIKE :name")
    fun getWayangByName(name: String): Flow<WayangEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWayang(wayang: WayangEntity)

    //STORIES
    @Query("SELECT * FROM stories_tb WHERE tokoh LIKE :tokoh")
    fun getAllStories(tokoh: String): Flow<List<StoriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<StoriesEntity>)

    //FAVORITE
    @Query("SELECT * FROM favorite_tb")
    fun getFavoriteWayang(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite_tb WHERE nm_wayang LIKE :name")
    fun getFavoriteWayangByName(name: String): Flow<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteWayang(wayang: FavoriteEntity)

    @Delete
    fun delFavoriteWayang(wayang: FavoriteEntity)

    //SEARCH
    @Query("SELECT * FROM search_tb WHERE nm_wayang LIKE :query")
    fun searchWayang(query: String): Flow<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchWayang(list: List<SearchEntity>)
}
