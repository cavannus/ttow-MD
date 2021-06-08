package id.cavannus.thetaleofwayang.core.domain.repository

import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import kotlinx.coroutines.flow.Flow

interface IWayangRepository {
    //HISTORY
    fun getAllWayang(): Flow<List<Wayang>>
    fun getWayangByName(query: String): Flow<Resource<List<Wayang>>>

    //FAVORITE
    fun getFavoriteWayang(): Flow<List<Wayang>>
    fun getFavoriteWayangByName(name: String): Flow<List<Wayang>>
    fun addFavoriteWayang(wayang: Wayang)
    fun delFavoriteWayang(wayang: Wayang)

    //STORIES
    fun getAllStories(query: String): Flow<Resource<List<Stories>>>

    //SEARCH
    fun searchWayang(query: String): Flow<Resource<List<Wayang>>>
}