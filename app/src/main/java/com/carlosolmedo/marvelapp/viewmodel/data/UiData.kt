package com.carlosolmedo.marvelapp.viewmodel.data

import com.carlosolmedo.marvelapp.model.Comic

data class ComicsList(val comics: List<Comic>, val message: String, val error: Throwable? = null)
