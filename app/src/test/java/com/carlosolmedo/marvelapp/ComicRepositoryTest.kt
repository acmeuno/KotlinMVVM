package com.carlosolmedo.marvelapp

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import com.carlosolmedo.marvelapp.model.remote.ComicApi
import com.carlosolmedo.marvelapp.model.Comic
import org.olu.mvvm.viewmodel.ComicRepository
import java.util.*
import kotlin.test.assertEquals


class ComicRepositoryTest {

    lateinit var comicRepository: ComicRepository
    lateinit var comicApi: ComicApi
    lateinit var comicDao: ComicDao

    @Before
    fun setup() {
        comicApi = mock()
        comicDao = mock()
        `when`(comicDao.getComics()).thenReturn(Single.just(emptyList()))
        comicRepository = ComicRepository(comicApi, comicDao)
    }

    @Test
    fun test_emptyCache_noDataOnApi_returnsEmptyList() {
        `when`(comicApi.getComics()).thenReturn(Observable.just(emptyList<Comic>()))

        comicRepository.getComics().test()
                .assertValue { it.isEmpty() }
    }

    @Test
    fun test_emptyCache_hasDataOnApi_returnsApiData() {
        `when`(comicApi.getComics()).thenReturn(Observable.just(listOf(aRandomUser())))

        comicRepository.getComics().test()
                .assertValueCount(1)
                .assertValue { it.size == 1 }
    }

    @Test
    fun test_hasCacheData_hasApiData_returnsBothData() {
        val cachedData = listOf(aRandomUser())
        val apiData = listOf(aRandomUser(), aRandomUser())
        `when`(comicApi.getComics()).thenReturn(Observable.just(apiData))
        comicRepository.cachedUsers = cachedData

        comicRepository.getComics().test()
                //Both cached & API data delivered
                .assertValueCount(2)
                //First cache data delivered
                .assertValueAt(0, { it == cachedData })
                //Secondly api data delivered
                .assertValueAt(1, { it == apiData })
    }

    @Test
    fun test_cache_updatedWithApiData() {
        val apiData = listOf(aRandomUser(), aRandomUser())
        `when`(comicApi.getComics()).thenReturn(Observable.just(apiData))

        comicRepository.getComics().test()

        assertEquals(comicRepository.cachedUsers, apiData)
    }

    fun aRandomUser() = Comic("mail@test.com", "John", UUID.randomUUID().toString().take(5))
}