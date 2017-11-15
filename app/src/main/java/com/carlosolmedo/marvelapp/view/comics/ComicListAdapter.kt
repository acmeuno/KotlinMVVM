package com.carlosolmedo.marvelapp.view.comics

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carlosolmedo.marvelapp.GlideApp
import com.carlosolmedo.marvelapp.R
import com.carlosolmedo.marvelapp.model.Comic
import kotlinx.android.synthetic.main.list_item_comic.view.*

/**
 * Created by carlosolmedo on 15/11/17.
 */
class ComicListAdapter : RecyclerView.Adapter<ComicListAdapter.ComicViewHolder>() {

    private var items : List<Comic> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder {
        return ComicViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_comic, parent, false))
    }


    override fun onBindViewHolder(holder: ComicViewHolder?, position: Int) {
        holder?.bindComic(items[position])
    }

    override fun getItemCount(): Int = items.size


    fun setDataSet(comics: List<Comic>) {
        items = comics
        notifyDataSetChanged()
    }

    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindComic(comic: Comic) {
            GlideApp.with(itemView)
                    .load(comic.thumbnail)
                    .centerCrop()
                    .into(itemView.imageView)
            itemView.textView.text = comic.title
        }

    }
}