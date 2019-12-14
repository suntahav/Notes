package com.sandstorm.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.sandstorm.notes.R
import com.sandstorm.notes.models.Task
import com.sandstorm.notes.models.Todo
import kotlinx.android.synthetic.main.activity_navigation.view.*
import kotlinx.android.synthetic.main.item_task.view.*

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(task: Task, todoCheckedCallback: (Int, Boolean) -> Unit) {
        descriptionTextView.text = task.title

        task.todos.forEachIndexed { todoIndex, todo ->
            val todoView = (LayoutInflater.from(context)
                .inflate(R.layout.view_todo, container, false) as TodoView).apply {
                initView(todo) { isChecked ->
                    todoCheckedCallback.invoke(todoIndex, isChecked)
                    if (isTaskComplete(task.todos)) {
                        createStrikeThrough()
                    } else {
                        removeStrikeThrough()
                    }
                }
            }
            todoContainer.addView(todoView)
        }
    }

    private fun isTaskComplete(todos: List<Todo>): Boolean = todos.none { !it.isComplete }

    private fun createStrikeThrough() {
        descriptionTextView.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        descriptionTextView.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}