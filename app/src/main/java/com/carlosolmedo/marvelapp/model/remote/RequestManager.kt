package com.carlosolmedo.marvelapp.model.remote

import com.carlosolmedo.marvelapp.model.Comic
import io.reactivex.Observable

/**
 * Created by carlosolmedo on 15/11/17.
 */
interface RequestManager {

    fun getComicsFromApi(): Observable<List<Comic>>
}