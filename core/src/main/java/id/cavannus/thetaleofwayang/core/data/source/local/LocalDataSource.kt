package id.cavannus.thetaleofwayang.core.data.source.local

import id.cavannus.thetaleofwayang.core.data.source.local.entity.FavoriteEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.SearchEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.StoriesEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import id.cavannus.thetaleofwayang.core.data.source.local.room.WayangDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val wayangDao: WayangDao) {
    //HISTORY
    fun getAllWayang(): Flow<List<WayangEntity>> = wayangDao.getAllWayang()
    fun getWayangByName(name: String): Flow<WayangEntity> = wayangDao.getWayangByName(name)
    suspend fun insertWayang(wayangList: List<WayangEntity>) =
            wayangDao.insertWayang(wayangList)
    fun addWayang(wayang: WayangEntity) =
            wayangDao.addWayang(wayang)

    //STORIES
    fun getAllStories(tokoh: String): Flow<List<StoriesEntity>> = wayangDao.getAllStories(tokoh)
    suspend fun insertStories(storiesList: List<StoriesEntity>) =
            wayangDao.insertStories(storiesList)

    //FAVORITE
    fun getFavoriteWayang(): Flow<List<FavoriteEntity>> = wayangDao.getFavoriteWayang()
    fun getFavoriteWayangByName(name: String): Flow<FavoriteEntity> =
            wayangDao.getFavoriteWayangByName(name)
    fun addFavoriteWayang(wayang: FavoriteEntity) = wayangDao.addFavoriteWayang(wayang)
    fun delFavoriteWayang(wayang: FavoriteEntity) = wayangDao.delFavoriteWayang(wayang)

    //SEARCH
    fun searchWayang(query: String) : Flow<List<SearchEntity>> = wayangDao.searchWayang(query)
    suspend fun insertSearchWayang(list: List<SearchEntity>) =
        wayangDao.insertSearchWayang(list)
}