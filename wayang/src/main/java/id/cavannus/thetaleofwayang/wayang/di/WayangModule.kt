package id.cavannus.thetaleofwayang.wayang.di

import id.cavannus.thetaleofwayang.wayang.detail.DetailWayangViewModel
import id.cavannus.thetaleofwayang.wayang.gallery.GalleryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wayangModule = module {
    viewModel { DetailWayangViewModel(get()) }
    viewModel { GalleryViewModel(get()) }
}