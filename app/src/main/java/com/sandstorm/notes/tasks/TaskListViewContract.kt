package com.sandstorm.notes.tasks

interface TaskListViewContract {
    fun onTodoCompleted(taskIndex: Int, todoIndex: Int, isComplete: Boolean)
}