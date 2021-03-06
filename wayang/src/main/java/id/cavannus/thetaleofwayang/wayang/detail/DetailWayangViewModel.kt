package id.cavannus.thetaleofwayang.wayang.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class DetailWayangViewModel(private val wayangUseCase: WayangUseCase) : ViewModel() {
    fun getWayangByName(query: String) = wayangUseCase.getWayangByName(query).asLiveData()

    fun getFavoriteByName(name: String) = wayangUseCase.getFavoriteWayangByName(name).asLiveData()
    fun addFavoriteWayang(wayang: Wayang) = wayangUseCase.addFavoriteWayang(wayang)
    fun delFavoriteWayang(wayang: Wayang) = wayangUseCase.delFavoriteWayang(wayang)

    fun getAllStories(query: String) = wayangUseCase.getAllStories(query).asLiveData()
}