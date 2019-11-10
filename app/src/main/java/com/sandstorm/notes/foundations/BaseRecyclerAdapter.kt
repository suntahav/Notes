package com.sandstorm.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sandstorm.notes.tasks.TaskAdapter

abstract class BaseRecyclerAdapter<T>(
    protected val masterlist: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return masterlist.size + 1
    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0)
            TYPE_ADD_BUTTON
        else
            TYPE_INFO

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is AddButtonViewHolder){
            holder.onBind(Unit)
        }
        else {
            (holder as BaseViewHolder<T>).onBind(masterlist[position-1])
        }
    }

    abstract class BaseViewHolder<E>(protected val view:View): RecyclerView.ViewHolder(view){
        abstract fun onBind(data : E)
    }

    abstract  class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }
}