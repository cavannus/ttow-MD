package id.cavannus.thetaleofwayang.core.di

import androidx.room.Room
import id.cavannus.thetaleofwayang.core.data.WayangRepository
import id.cavannus.thetaleofwayang.core.data.source.local.LocalDataSource
import id.cavannus.thetaleofwayang.core.data.source.local.room.WayangDatabase
import id.cavannus.thetaleofwayang.core.data.source.remote.network.ApiService
import id.cavannus.thetaleofwayang.core.data.source.remote.RemoteDataSource
import id.cavannus.thetaleofwayang.core.domain.repository.IWayangRepository
import id.cavannus.thetaleofwayang.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<WayangDatabase>().wayangDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            WayangDatabase::class.java, "Wayang.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            //.baseUrl("https://wayangapi.herokuapp.com/api/")
            .baseUrl("https://wayang-314813.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IWayangRepository> { WayangRepository(get(), get(), get()) }
}