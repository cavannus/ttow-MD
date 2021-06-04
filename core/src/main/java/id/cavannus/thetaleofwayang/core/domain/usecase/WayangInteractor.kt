package id.cavannus.thetaleofwayang.core.domain.usecase

import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.domain.repository.IWayangRepository
import kotlinx.coroutines.flow.Flow

class WayangInteractor(private val wayangRepository: IWayangRepository): WayangUseCase {

    override fun getAllWayang() = wayangRepository.getAllWayang()

    override fun getAllStories(query: String) = wayangRepository.getAllStories(query)

    override fun getFavoriteWayang() = wayangRepository.getFavoriteWayang()

    override fun setFavoriteWayang(wayang: Wayang, state: Boolean) = wayangRepository.setFavoriteWayang(wayang, state)

    override fun searchWayang(query: String): Flow<Resource<List<Wayang>>> = wayangRepository.searchWayang(query)
}