package com.carlosolmedo.marvelapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.users_fragment.*
import com.carlosolmedo.marvelapp.App
import com.carlosolmedo.marvelapp.R
import com.carlosolmedo.marvelapp.model.remote.RemoteMapper
import com.carlosolmedo.marvelapp.viewmodel.data.ComicsList
import timber.log.Timber
import java.net.ConnectException

class ComicsListFragment : MvvmFragment() {

    val comicListViewModel = App.injectUserListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        subscribe(comicListViewModel.getComics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received UIModel with ${it.comics.size} comics.")
                    showComics(it)
                }, {
                    Timber.d(it)
                    showError(it)
                }))
    }

    fun showComics(data: ComicsList) {
        if (data.error == null) {
            usersList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, data.comics)
        } else if (data.error is ConnectException) {
            Timber.d("No connection, maybe inform user that data loaded from DB.")
        } else {
            showError(data.error)
        }
    }

    fun showError(t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
    }
}
