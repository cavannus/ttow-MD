package id.cavannus.thetaleofwayang.wayang.wayangku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class StoredWayangViewModel(wayangUseCase: WayangUseCase) : ViewModel() {
    val favoriteWayang = wayangUseCase.getFavoriteWayang().asLiveData()
}