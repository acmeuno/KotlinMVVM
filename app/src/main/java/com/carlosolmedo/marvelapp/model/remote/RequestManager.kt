package com.carlosolmedo.marvelapp.model.remote

import com.carlosolmedo.marvelapp.BuildConfig
import com.carlosolmedo.marvelapp.extensions.HASH
import com.carlosolmedo.marvelapp.model.Comic
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by carlosolmedo on 15/11/17.
 */
class RequestManager(val comicApi: ComicApi) {


    fun getComicsFromApi(): Observable<List<Comic>> {

        val timestamp: Long = 1
        //val timestamp = Date().time
        return comicApi.getComics(timestamp, BuildConfig.PUBLIC_KEY, HASH.md5("$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"))
                .map {
                    it.data?.comics?.map { RemoteMapper.toComicModel(it) } ?: emptyList()
                }
                .doOnNext {
                    Timber.d("Dispatching ${it.size} comics from API...")
                    //storeComicsInDb(it)
                }
    }


}
