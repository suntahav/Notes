package com.sandstorm.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandstorm.notes.R
import com.sandstorm.notes.foundations.BaseRecyclerAdapter
import com.sandstorm.notes.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(
    noteList: MutableList<Note> = mutableListOf()
) : BaseRecyclerAdapter<Note>(noteList){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))
    }

    class ViewHolder(view: View) : BaseViewHolder<Note>(view){
        override fun onBind(note:Note){
                 view.descriptionTextView.text = note.description
        }
    }
}