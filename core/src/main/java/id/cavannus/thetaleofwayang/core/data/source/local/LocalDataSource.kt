package id.cavannus.thetaleofwayang.core.data.source.local

import id.cavannus.thetaleofwayang.core.data.source.local.entity.StoriesEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import id.cavannus.thetaleofwayang.core.data.source.local.room.WayangDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val wayangDao: WayangDao) {

    fun getAllWayang(): Flow<List<WayangEntity>> = wayangDao.getAllWayang()

    fun getAllStories(): Flow<List<StoriesEntity>> = wayangDao.getAllStories()

    suspend fun insertWayang(wayangList: List<WayangEntity>) = wayangDao.insertWayang(wayangList)

    suspend fun insertStories(storiesList: List<StoriesEntity>) = wayangDao.insertStories(storiesList)

    fun getFavoriteWayang(): Flow<List<WayangEntity>> = wayangDao.getFavoriteWayang()

    fun setFavoriteWayang(wayang: WayangEntity, newState: Boolean) {
        wayang.isFavorite = newState
        wayangDao.updateFavoriteWayang(wayang)
    }
}