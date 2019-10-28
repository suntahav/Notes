package com.sandstorm.notes.notes


import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.sandstorm.notes.R
import com.sandstorm.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.*


class NotesListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(
            mutableListOf(
                Note(description = "Note 1 description"),
                Note(description = "Note 2 description")
            )
        )
        recyclerView.adapter = adapter
    }
    companion object {

        fun newInstance() = NotesListFragment()

    }

}
