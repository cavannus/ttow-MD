package id.cavannus.thetaleofwayang.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class GalleryViewModel(wayangUseCase: WayangUseCase) : ViewModel() {
    val wayang = wayangUseCase.getAllWayang().asLiveData()
}