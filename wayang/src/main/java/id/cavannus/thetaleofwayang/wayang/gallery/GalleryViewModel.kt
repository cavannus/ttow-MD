package id.cavannus.thetaleofwayang.wayang.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class GalleryViewModel(wayangUseCase: WayangUseCase) : ViewModel() {
    val wayang = wayangUseCase.getAllWayang().asLiveData()
}