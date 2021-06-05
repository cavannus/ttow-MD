package id.cavannus.thetaleofwayang.core.domain.usecase

import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import kotlinx.coroutines.flow.Flow

interface WayangUseCase {
    fun getAllWayang(): Flow<Resource<List<Wayang>>>
    fun getAllStories(query: String): Flow<Resource<List<Stories>>>
    fun getFavoriteWayang(): Flow<List<Wayang>>
    fun getFavoriteWayangByName(name: String): Flow<Wayang>
    fun setFavoriteWayang(wayang: Wayang, state: Boolean)
    fun searchWayang(query: String): Flow<Resource<List<Wayang>>>
}