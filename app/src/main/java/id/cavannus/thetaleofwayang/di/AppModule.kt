package id.cavannus.thetaleofwayang.di

import id.cavannus.thetaleofwayang.core.domain.usecase.WayangInteractor
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase
import id.cavannus.thetaleofwayang.detail.DetailWayangViewModel
import id.cavannus.thetaleofwayang.ui.gallery.GalleryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<WayangUseCase> { WayangInteractor(get()) }
}

val viewModelModule = module {
    //viewModel { HomeViewModel(get()) }
    viewModel { GalleryViewModel(get()) }
    viewModel { DetailWayangViewModel(get()) }
}