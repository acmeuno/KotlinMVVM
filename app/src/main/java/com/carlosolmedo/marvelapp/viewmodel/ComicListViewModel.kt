package com.carlosolmedo.marvelapp.viewmodel

import io.reactivex.Observable
import com.carlosolmedo.marvelapp.viewmodel.data.ComicsList
import org.olu.mvvm.viewmodel.ComicRepository
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ComicListViewModel(val comicRepository: ComicRepository) {

    fun getComics(): Observable<ComicsList> {
        //Create the data for your UI, the comics lists and maybe some additional data needed as well
        return comicRepository.getComics()
                //Drop DB data if we can fetch item fast enough from the API
                //to avoid UI flickers
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping comics to UIData...")
                    ComicsList(it.take(10), "Top 10 Users")
                }
                .onErrorReturn {
                    ComicsList(emptyList(), "An error occurred", it)
                }
    }
}
