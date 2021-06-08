package id.cavannus.thetaleofwayang.di

import id.cavannus.thetaleofwayang.core.domain.usecase.WayangInteractor
import id.cavannus.thetaleofwayang.core.domain.usecase.WayangUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<WayangUseCase> { WayangInteractor(get()) }
}
