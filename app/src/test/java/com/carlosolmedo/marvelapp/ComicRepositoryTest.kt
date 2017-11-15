package com.carlosolmedo.marvelapp

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import com.carlosolmedo.marvelapp.model.remote.ComicApi
import com.carlosolmedo.marvelapp.model.Comic
import com.carlosolmedo.marvelapp.model.remote.RequestManager
import com.carlosolmedo.marvelapp.model.remote.RequestManagerImpl
import org.olu.mvvm.viewmodel.ComicRepository
import java.util.*
import kotlin.test.assertEquals


class ComicRepositoryTest {

    lateinit var comicRepository: ComicRepository
    lateinit var comicApi: ComicApi

    @Before
    fun setup() {
        comicApi = mock()
        // `when`(comicApi.getComics(1, BuildConfig.PUBLIC_KEY,"")).thenReturn(Observable.just(emptyList()))
        comicRepository = ComicRepository(RequestManagerImpl(comicApi))
    }

    //Simulamos una situación en la que nos llega una lista vacía
    @Test
    fun test_noDataOnApi_returnsEmptyList() {
        `when`(comicRepository.getComics()).thenReturn(Observable.just(emptyList()))

        comicRepository.getComics().test().assertValue { it.isEmpty() }
    }

    fun aRandomComic() = Comic(1)
}