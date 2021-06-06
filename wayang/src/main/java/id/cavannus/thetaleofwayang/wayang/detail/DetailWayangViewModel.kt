package id.cavannus.thetaleofwayang.wayang.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class DetailWayangViewModel(private val wayangUseCase: WayangUseCase) : ViewModel() {
    fun setFavoriteWayang(wayang: Wayang, newStatus:Boolean) = wayangUseCase.setFavoriteWayang(wayang, newStatus)
    fun getAllStories(query: String) = wayangUseCase.getAllStories(query).asLiveData()
    fun getFavorite(name: String) = wayangUseCase.getFavoriteWayangByName(name).asLiveData()
}