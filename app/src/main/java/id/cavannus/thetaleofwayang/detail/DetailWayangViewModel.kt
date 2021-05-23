package id.cavannus.thetaleofwayang.detail

import androidx.lifecycle.ViewModel
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class DetailWayangViewModel(private val wayangUseCase: WayangUseCase) : ViewModel() {
    fun setFavoriteWayang(wayang: Wayang, newStatus:Boolean) = wayangUseCase.setFavoriteWayang(wayang, newStatus)
}

