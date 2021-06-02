package id.cavannus.thetaleofwayang.di

import id.cavannus.thetaleofwayang.core.domain.usecase.WayangInteractor
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<WayangUseCase> { WayangInteractor(get()) }
    //factory<StoriesUseCase> { StoriesInteractor(get()) }
}

//val viewModelModule = module {
//    //viewModel { HomeViewModel(get()) }
//    //viewModel { GalleryViewModel(get()) }
//    //viewModel { DetailWayangViewModel(get()) }
//}