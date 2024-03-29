package com.sandstorm.notes.foundations

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(
    protected val masterlist: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun updateList(list: List<T>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallBackImpl(masterlist,list))
        masterlist.clear()
        masterlist.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return masterlist.size + 1
    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0)
            TYPE_ADD_BUTTON
        else
            TYPE_INFO

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder) {
            holder.onBind(Unit, position - 1)
        } else {
            (holder as BaseViewHolder<T>).onBind(masterlist[position - 1], position - 1)
        }
    }

    abstract class BaseViewHolder<E>(protected val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: E, listIndex: Int)
    }

    abstract class AddButtonViewHolder(view: View) : BaseViewHolder<Unit>(view)

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

    class DiffUtilCallBackImpl<T>(val oldList: List<T>, val newList: List<T>) : DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition]==newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition]==newList[newItemPosition]

    }
}