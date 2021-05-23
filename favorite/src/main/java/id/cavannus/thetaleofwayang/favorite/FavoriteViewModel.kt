package id.cavannus.thetaleofwayang.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class FavoriteViewModel(wayangUseCase: WayangUseCase) : ViewModel() {
    val favoriteWayang = wayangUseCase.getFavoriteWayang().asLiveData()
}