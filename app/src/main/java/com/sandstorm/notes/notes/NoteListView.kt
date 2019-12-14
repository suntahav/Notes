package com.sandstorm.notes.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandstorm.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.view.*

class NoteListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private lateinit var adapter: NoteAdapter
    private lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate
    private lateinit var dataDelegate: NoteListViewContract

    fun initView(
        taDelegate: NotesListFragment.TouchActionDelegate,
        daDelegate: NoteListViewContract
    ) {
        setDelegate(taDelegate, daDelegate)
        setupView()
    }

    private fun setDelegate(
        taDelegate: NotesListFragment.TouchActionDelegate,
        daDelegate: NoteListViewContract
    ) {
        touchActionDelegate = taDelegate
        dataDelegate = daDelegate
    }

    private fun setupView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataDelegate
        )
        recyclerView.adapter = adapter
    }

    fun updateList(list: List<Note>) {
        adapter.updateList(list)
    }
}