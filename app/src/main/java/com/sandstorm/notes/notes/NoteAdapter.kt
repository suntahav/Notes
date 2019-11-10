package com.sandstorm.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandstorm.notes.R
import com.sandstorm.notes.foundations.BaseRecyclerAdapter
import com.sandstorm.notes.models.Note
import com.sandstorm.notes.navigation.NavigationActivity
import com.sandstorm.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*

class NoteAdapter(
    noteList: MutableList<Note> = mutableListOf(),
    val touchActionDelegate: NotesListFragment.TouchActionDelegate
) : BaseRecyclerAdapter<Note>(noteList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ADD_BUTTON) {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_add_button,
                    parent,
                    false
                )
            )
        } else {
            NoteViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_note,
                    parent,
                    false
                )
            )
        }
    }

    class NoteViewHolder(view: View) : BaseViewHolder<Note>(view) {
        override fun onBind(data: Note) {
            (view as NoteView).initView(data)
        }
    }

    inner class AddButtonViewHolder(view: View) : BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(data: Unit) {
            view.buttonText.text = view.context.getString(R.string.add_button_note)

            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_NOTE)
            }
        }
    }
}