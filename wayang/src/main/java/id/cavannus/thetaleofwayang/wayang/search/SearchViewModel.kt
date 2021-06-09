package id.cavannus.thetaleofwayang.wayang.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase

class SearchViewModel(private val wayangUseCase: WayangUseCase) : ViewModel() {
    fun searchWayang(query: String) = wayangUseCase.searchWayang(query).asLiveData()
}