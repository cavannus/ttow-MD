package id.cavannus.thetaleofwayang.core.domain.usecase

import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import kotlinx.coroutines.flow.Flow

interface WayangUseCase {
    //HISTORY
    fun getAllWayang(): Flow<List<Wayang>>
    fun getWayangByName(query: String): Flow<Resource<List<Wayang>>>

    //STORIES
    fun getAllStories(query: String): Flow<Resource<List<Stories>>>

    //FAVORITE
    fun getFavoriteWayang(): Flow<List<Wayang>>
    fun getFavoriteWayangByName(name: String): Flow<List<Wayang>>
    fun addFavoriteWayang(wayang: Wayang)
    fun delFavoriteWayang(wayang: Wayang)

    //SEARCH
    fun searchWayang(query: String): Flow<Resource<List<Wayang>>>
}