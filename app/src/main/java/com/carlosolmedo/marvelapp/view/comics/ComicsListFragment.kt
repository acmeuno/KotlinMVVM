package com.carlosolmedo.marvelapp.view.comics

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_comics.*
import com.carlosolmedo.marvelapp.App
import com.carlosolmedo.marvelapp.R
import com.carlosolmedo.marvelapp.view.MvvmFragment
import com.carlosolmedo.marvelapp.viewmodel.data.ComicsList
import timber.log.Timber
import java.net.ConnectException

class ComicsListFragment : MvvmFragment() {

    val comicListViewModel = App.injectUserListViewModel()
    val adapter = ComicListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
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
            adapter.setDataSet(data.comics)
        } else if (data.error is ConnectException) {
            Timber.d("No connection, maybe inform user that data loaded from DB.")
        } else {
            Timber.d(data.error)
            showError(data.error)
        }
    }

    fun showError(t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
    }
}
