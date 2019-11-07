package com.sandstorm.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandstorm.notes.R
import com.sandstorm.notes.foundations.BaseRecyclerAdapter
import com.sandstorm.notes.models.Task
import kotlinx.android.synthetic.main.activity_navigation.view.*
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_todo.view.*

class TaskAdapter(taskList: MutableList<Task> = mutableListOf()) :
    BaseRecyclerAdapter<Task>(taskList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    class ViewHolder(view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(task: Task) {
            view.descriptionTextView.text = task.title
            task.todos.forEach { todo ->
                val todoView = LayoutInflater.from(view.context)
                    .inflate(R.layout.view_todo, view.container, false).apply {
                        descriptionView.text = todo.description
                        checkBox.isChecked = todo.isComplete
                    }
                view.todoContainer.addView(todoView)
            }
        }
    }
}