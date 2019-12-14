package com.sandstorm.notes.foundations

import com.sandstorm.notes.notes.INoteModel
import com.sandstorm.notes.notes.NoteLocalModel
import com.sandstorm.notes.tasks.ITaskModel
import com.sandstorm.notes.tasks.TaskLocalModel
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope: Scope = Toothpick.openScope(this).apply {
        installModules(ApplicationModule)
    }
}

object ApplicationModule: Module(){
    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}