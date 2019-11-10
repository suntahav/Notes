package com.sandstorm.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandstorm.notes.R
import com.sandstorm.notes.foundations.BaseRecyclerAdapter
import com.sandstorm.notes.models.Task
import com.sandstorm.notes.navigation.NavigationActivity
import com.sandstorm.notes.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*

class TaskAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    val touchActionDelegate: TasksListFragment.TouchActionDelegate
) :
    BaseRecyclerAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ADD_BUTTON) {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_add_button,
                    parent,
                    false
                )
            )
        } else {
            TaskViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_task,
                    parent,
                    false
                )
            )
        }
    }

    class TaskViewHolder(view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(data: Task) {
            (view as TaskView).initView(data)
        }
    }

    inner class AddButtonViewHolder(view: View) : BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(data: Unit) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)

            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_TASK)
            }
        }
    }


}