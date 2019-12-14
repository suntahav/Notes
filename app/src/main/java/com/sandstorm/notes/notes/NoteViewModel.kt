package com.sandstorm.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandstorm.notes.foundations.ApplicationScope
import com.sandstorm.notes.models.Note
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class NoteViewModel : ViewModel(), NoteListViewContract {
    @Inject
    lateinit var model: INoteModel

    private val _noteList: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteList: LiveData<MutableList<Note>> = _noteList

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        _noteList.postValue(model.getFakeData())
    }


}