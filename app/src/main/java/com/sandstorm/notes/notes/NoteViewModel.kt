package com.sandstorm.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandstorm.notes.models.Note

class NoteViewModel : ViewModel(), NoteListViewContract {
    private val model = NoteModel()
    private val _noteList: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteList: LiveData<MutableList<Note>> = _noteList

    init {
        _noteList.postValue(model.getFakeData())
    }


}