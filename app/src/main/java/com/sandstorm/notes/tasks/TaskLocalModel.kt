package com.sandstorm.notes.tasks

import com.sandstorm.notes.models.Task
import com.sandstorm.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel{
    override fun addTask(task: Task, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTask(task: Task, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(task: Task, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFakeData(): MutableList<Task> = mutableListOf(
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