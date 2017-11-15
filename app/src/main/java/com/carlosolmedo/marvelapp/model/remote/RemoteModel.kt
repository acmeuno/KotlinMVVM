package com.carlosolmedo.marvelapp.model.remote

import com.google.gson.annotations.SerializedName


data class ComicDataWrapper(
		val code: Int?, //200
		val status: String?, //Ok
		val copyright: String?, //© 2017 MARVEL
		val attributionText: String?, //ComicDataContainer provided by Marvel. © 2017 MARVEL
		val attributionHTML: String?, //<a href="http://marvel.com">ComicDataContainer provided by Marvel. © 2017 MARVEL</a>
		val etag: String?, //da2494d392c241dbd3f9e11cffcebd36d43d937e
		val data: ComicDataContainer?
)

data class ComicDataContainer(
		val offset: Int?, //0
		val limit: Int?, //1
		val total: Int?, //40586
		val count: Int?, //1
        @SerializedName("results")
		val comics: List<ComicRemote>?
)

data class ComicRemote(
		val id: Int?, //21471
		val digitalId: Int?, //0
		val title: String?, //Ultimate Spider-Man (Spanish Language Edition) (2000) #7
		val issueNumber: Int?, //7
		val variantDescription: String?,
		val description: Any?, //null
		val modified: String?, //-0001-11-30T00:00:00-0500
		val isbn: String?,
		val upc: String?,
		val diamondCode: String?,
		val ean: String?,
		val issn: String?,
		val format: String?, //ComicRemote
		val pageCount: Int?, //36
		val textObjects: List<Any?>?,
		val resourceURI: String?, //http://gateway.marvel.com/v1/public/comics/21471
		val urls: List<Url?>?,
		val series: Series?,
		val variants: List<Any?>?,
		val collections: List<Any?>?,
		val collectedIssues: List<Any?>?,
		val dates: List<Date?>?,
		val prices: List<Price?>?,
		val thumbnail: Thumbnail?,
		val images: List<Any?>?,
		val creators: Creators?,
		val characters: Characters?,
		val stories: Stories?,
		val events: Events?
)

data class Events(
		val available: Int?, //0
		val collectionURI: String?, //http://gateway.marvel.com/v1/public/comics/21471/events
		val items: List<Any?>?,
		val returned: Int? //0
)

data class Thumbnail(
		val path: String?, //http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available
		val extension: String? //jpg
)

data class Stories(
		val available: Int?, //2
		val collectionURI: String?, //http://gateway.marvel.com/v1/public/comics/21471/stories
		val items: List<Item?>?,
		val returned: Int? //2
)

data class Item(
		val resourceURI: String?, //http://gateway.marvel.com/v1/public/stories/48970
		val name: String?, //Cover #48970
		val type: String? //cover
)

data class Characters(
		val available: Int?, //1
		val collectionURI: String?, //http://gateway.marvel.com/v1/public/comics/21471/characters
		val items: List<Item?>?,
		val returned: Int? //1
)

data class Url(
		val type: String?, //detail
		val url: String? //http://marvel.com/comics/issue/21471/ultimate_spider-man_spanish_language_edition_2000_7?utm_campaign=apiRef&utm_source=6fe4ae0bb8534e49e606a02da66a9f51
)

data class Date(
		val type: String?, //onsaleDate
		val date: String? //2029-12-31T00:00:00-0500
)

data class Series(
		val resourceURI: String?, //http://gateway.marvel.com/v1/public/series/5105
		val name: String? //Ultimate Spider-Man (Spanish Language Edition) (2000 - 2001)
)

data class Creators(
		val available: Int?, //0
		val collectionURI: String?, //http://gateway.marvel.com/v1/public/comics/21471/creators
		val items: List<Any?>?,
		val returned: Int? //0
)

data class Price(
		val type: String?, //printPrice
		val price: Int? //0
)