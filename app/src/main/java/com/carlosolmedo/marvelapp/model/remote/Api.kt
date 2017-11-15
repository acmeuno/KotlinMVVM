package com.carlosolmedo.marvelapp.model.remote

import io.reactivex.Observable
import retrofit2.http.*

interface ComicApi {

    @Headers("Referer: developer.marvel.com")
    @GET("comics")
    fun getComics(@Query("ts") timestamp: Long, @Query("apikey") apiKey: String, @Query("hash") hash: String): Observable<ComicDataWrapper>
}
