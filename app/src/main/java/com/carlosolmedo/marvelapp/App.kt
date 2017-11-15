package com.carlosolmedo.marvelapp

import android.app.Application
import com.carlosolmedo.marvelapp.model.remote.ComicApi
import com.carlosolmedo.marvelapp.model.remote.RequestManager
import com.carlosolmedo.marvelapp.model.remote.RequestManagerImpl
import com.carlosolmedo.marvelapp.viewmodel.ComicListViewModel
import org.olu.mvvm.viewmodel.ComicRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class App : Application() {

    //For the sake of simplicity, we use this instead of Dagger
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var comicApi: ComicApi
        private lateinit var comicRepository: ComicRepository
        private lateinit var requestManager: RequestManager
        private lateinit var comicListViewModel: ComicListViewModel
        //private lateinit var appDatabase: AppDatabase

        fun injectUserApi() = comicApi

        fun injectUserListViewModel() = comicListViewModel

        //fun injectUserDao() = appDatabase.comicDao()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .build()

        comicApi = retrofit.create(ComicApi::class.java)

       /* appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "marvelapp-database")
                .fallbackToDestructiveMigration()
                .build()
*/

        requestManager = RequestManagerImpl(comicApi)

        comicRepository = ComicRepository(requestManager)

        comicListViewModel = ComicListViewModel(comicRepository)
    }

}
