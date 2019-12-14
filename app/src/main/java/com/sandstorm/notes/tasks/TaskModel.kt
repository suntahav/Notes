package com.sandstorm.notes.tasks

import com.sandstorm.notes.models.Task
import com.sandstorm.notes.models.Todo

class TaskModel {

    fun getFakeData(): MutableList<Task> = mutableListOf(
        Task(
            title = "Test task 1", todos = mutableListOf(
                Todo(description = "Test One!", isComplete = true),
                Todo(description = "Test two!")
            )
        ),
        Task(title = "Test task 2"),
        Task(
            title = "Test Task 3", todos = mutableListOf(
                Todo(description = "Test A"),
                Todo(description = "Test B")
            )
        )
    )
}