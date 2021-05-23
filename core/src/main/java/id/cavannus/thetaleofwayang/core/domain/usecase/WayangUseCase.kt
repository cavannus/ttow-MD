package id.cavannus.thetaleofwayang.core.domain.usecase

import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import kotlinx.coroutines.flow.Flow

interface WayangUseCase {
    fun getAllWayang(): Flow<Resource<List<Wayang>>>
    fun getFavoriteWayang(): Flow<List<Wayang>>
    fun setFavoriteWayang(wayang: Wayang, state: Boolean)
}