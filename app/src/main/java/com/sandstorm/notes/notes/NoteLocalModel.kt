package com.sandstorm.notes.notes

import android.util.Log
import com.sandstorm.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel{
    override fun addNote(note: Note, callback: SuccessCallback) {
        Log.d("MVVM Note",note.toString())
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveNotes(): List<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFakeData(): MutableList<Note> = mutableListOf(
        Note(description = "Note 1 description"),
        Note(description = "Note 2 description")
    )
}