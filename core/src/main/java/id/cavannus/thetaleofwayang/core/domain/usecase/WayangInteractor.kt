package id.cavannus.thetaleofwayang.core.domain.usecase

import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.domain.repository.IWayangRepository

class WayangInteractor(private val wayangRepository: IWayangRepository): WayangUseCase {

    override fun getAllWayang() = wayangRepository.getAllWayang()

    override fun getAllStories() = wayangRepository.getAllStories()

    override fun getFavoriteWayang() = wayangRepository.getFavoriteWayang()

    override fun setFavoriteWayang(wayang: Wayang, state: Boolean) = wayangRepository.setFavoriteWayang(wayang, state)
}