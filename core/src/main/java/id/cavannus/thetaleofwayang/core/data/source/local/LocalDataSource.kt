package id.cavannus.thetaleofwayang.core.data.source.local

import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import id.cavannus.thetaleofwayang.core.data.source.local.room.WayangDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val wayangDao: WayangDao) {

    fun getAllWayang(): Flow<List<WayangEntity>> = wayangDao.getAllWayang()

    fun getFavoriteWayang(): Flow<List<WayangEntity>> = wayangDao.getFavoriteWayang()

    suspend fun insertWayang(wayangList: List<WayangEntity>) = wayangDao.insertWayang(wayangList)

    fun setFavoriteWayang(wayang: WayangEntity, newState: Boolean) {
        wayang.isFavorite = newState
        wayangDao.updateFavoriteWayang(wayang)
    }
}