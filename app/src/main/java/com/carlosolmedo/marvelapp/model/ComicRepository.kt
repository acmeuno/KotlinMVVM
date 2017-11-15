package org.olu.mvvm.viewmodel

import io.reactivex.Observable
import com.carlosolmedo.marvelapp.model.Comic
import com.carlosolmedo.marvelapp.model.remote.RequestManager

class ComicRepository(val requestManager: RequestManager) {

    fun getComics(): Observable<List<Comic>> {
        return Observable.concatArray(
                //getComicsFromDb(),
                requestManager.getComicsFromApi())
    }


   /* fun getComicsFromDb(): Observable<List<Comic>> {
        return comicDao.getComics().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} comics from DB...")
                }
    }*/

    /*fun getComicsFromApi(): Observable<List<Comic>> {


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
    }*/

    /*fun storeComicsInDb(comics: List<Comic>) {
        Observable.fromCallable { comicDao.insertAll(comics) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${comics.size} comics from API in DB...")
                }
    }*/

}
