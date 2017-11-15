package com.carlosolmedo.marvelapp.model.remote

import com.carlosolmedo.marvelapp.model.Comic

/**
 * Created by carlosolmedo on 14/11/17.
 */
internal object RemoteMapper {


    fun toComicModel(comic: ComicRemote) : Comic {

        return Comic(comic.id ?: 0)
    }

}