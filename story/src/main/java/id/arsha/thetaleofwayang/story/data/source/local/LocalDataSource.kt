package id.arsha.thetaleofwayang.story.data.source.local

//import id.arsha.thetaleofwayang.story.data.source.local.entity.StoriesEntity
//import id.arsha.thetaleofwayang.story.data.source.local.room.StoriesDao
//import kotlinx.coroutines.flow.Flow
//
//class LocalDataSource(private val storiesDao: StoriesDao) {
//
//    fun getAllStories(): Flow<List<StoriesEntity>> = storiesDao.getAllStories()
//
//    fun getFavoriteStories(): Flow<List<StoriesEntity>> = storiesDao.getFavoriteStories()
//
//    suspend fun insertStories(storiesList: List<StoriesEntity>) = storiesDao.insertStories(storiesList)
//
//    fun setFavoriteStories(stories: StoriesEntity, newState: Boolean) {
//        stories.isFavorite = newState
//        storiesDao.updateFavoriteStories(stories)
//    }
//}