package id.arsha.thetaleofwayang.story.di

//import androidx.room.Room
//import id.arsha.thetaleofwayang.story.data.StoriesRepository
//import id.arsha.thetaleofwayang.story.data.source.local.LocalDataSource
//import id.arsha.thetaleofwayang.story.data.source.local.room.StoriesDatabase
//import id.arsha.thetaleofwayang.story.data.source.remote.RemoteDataSource
//import id.arsha.thetaleofwayang.story.data.source.remote.network.ApiService
//import id.arsha.thetaleofwayang.story.domain.repository.IStoriesRepository
//import id.arsha.thetaleofwayang.story.utils.AppExecutors
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import org.koin.android.ext.koin.androidContext
//import org.koin.dsl.module
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//val databaseModuleStories = module {
//    factory { get<StoriesDatabase>().storiesDao() }
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            StoriesDatabase::class.java, "Stories.db"
//        ).fallbackToDestructiveMigration().build()
//    }
//}
//
//val networkModuleStories = module {
//    single {
//        OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .build()
//    }
//    single {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://wayang-314813.et.r.appspot.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(get())
//            .build()
//        retrofit.create(ApiService::class.java)
//    }
//}
//
//val repositoryModuleStories = module {
//    single { LocalDataSource(get()) }
//    single { RemoteDataSource(get()) }
//    factory { AppExecutors() }
//    single<IStoriesRepository> { StoriesRepository(get(), get(), get()) }
//}