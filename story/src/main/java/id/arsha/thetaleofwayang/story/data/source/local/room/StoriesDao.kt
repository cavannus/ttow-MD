package id.arsha.thetaleofwayang.story.data.source.local.room

//import androidx.room.*
//import id.arsha.thetaleofwayang.story.data.source.local.entity.StoriesEntity
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface StoriesDao {
//
//    @Query("SELECT * FROM stories")
//    fun getAllStories(): Flow<List<StoriesEntity>>
//
//    @Query("SELECT * FROM stories where isFavorite = 1")
//    fun getFavoriteStories(): Flow<List<StoriesEntity>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertStories(stories: List<StoriesEntity>)
//
//    @Update
//    fun updateFavoriteStories(stories: StoriesEntity)
//}
