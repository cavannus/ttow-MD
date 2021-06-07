package id.cavannus.thetaleofwayang.core.domain.usecase

import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.domain.repository.IWayangRepository
import kotlinx.coroutines.flow.Flow

class WayangInteractor(private val wayangRepository: IWayangRepository): WayangUseCase {
    //HISTORY
    override fun getAllWayang() = wayangRepository.getAllWayang()

    //STORIES
    override fun getAllStories(query: String) = wayangRepository.getAllStories(query)

    //FAVORITE
    override fun getFavoriteWayang() = wayangRepository.getFavoriteWayang()
    override fun getFavoriteWayangByName(name: String): Flow<Wayang?> = wayangRepository.getFavoriteWayangByName(name)
    override fun addFavoriteWayang(wayang: Wayang) = wayangRepository.addFavoriteWayang(wayang)
    override fun delFavoriteWayang(wayang: Wayang) = wayangRepository.delFavoriteWayang(wayang)

    //SEARCH
    override fun searchWayang(query: String): Flow<Resource<List<Wayang>>> = wayangRepository.searchWayang(query)
}