package com.sandstorm.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(
    protected val masterlist: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return masterlist.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<T>).onBind(masterlist[position])
    }

    abstract class BaseViewHolder<E>(protected val view:View): RecyclerView.ViewHolder(view){
        abstract fun onBind(data : E)
    }
}