package id.cavannus.thetaleofwayang.core.domain.repository

import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import kotlinx.coroutines.flow.Flow

interface IWayangRepository {

    fun getAllWayang(): Flow<Resource<List<Wayang>>>

    fun getFavoriteWayang(): Flow<List<Wayang>>

    fun setFavoriteWayang(wayang: Wayang, state: Boolean)

    fun getAllStories(): Flow<Resource<List<Stories>>>

}