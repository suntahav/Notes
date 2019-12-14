package com.sandstorm.notes.notes

import com.sandstorm.notes.models.Note

class NoteModel {
    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note(description = "Note 1 description"),
        Note(description = "Note 2 description")
    )
}