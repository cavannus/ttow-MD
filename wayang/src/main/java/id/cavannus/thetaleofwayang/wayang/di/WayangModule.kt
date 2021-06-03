package id.cavannus.thetaleofwayang.wayang.di

import id.cavannus.thetaleofwayang.wayang.detail.DetailWayangViewModel
import id.cavannus.thetaleofwayang.wayang.gallery.GalleryViewModel
import id.cavannus.thetaleofwayang.wayang.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wayangModule = module {
    viewModel { DetailWayangViewModel(get()) }
    viewModel { GalleryViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}