package com.sandstorm.notes.create

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sandstorm.notes.R
import com.sandstorm.notes.foundations.ApplicationScope
import com.sandstorm.notes.foundations.NullFieldChecker
import com.sandstorm.notes.foundations.StateChangeTextWatcher
import com.sandstorm.notes.models.Task
import com.sandstorm.notes.models.Todo
import com.sandstorm.notes.tasks.ITaskModel
import com.sandstorm.notes.tasks.TaskLocalModel
import com.sandstorm.notes.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import toothpick.Toothpick
import javax.inject.Inject

private const val MAX_TODO_COUNT = 5

class CreateTaskFragment : Fragment() {
    @Inject
    lateinit var model: ITaskModel
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, ApplicationScope.scope)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTaskView.taskEditText.addTextChangedListener(object : StateChangeTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                }
                super.afterTextChanged(s)
            }

        })
    }

    private fun addTodoView() {
        if (canAddTodos()) {
            val view = (LayoutInflater.from(context).inflate(
                R.layout.view_create_todo,
                containerView,
                false
            ) as CreateTodoView).apply {
                todoEditText.addTextChangedListener(object : StateChangeTextWatcher() {
                    override fun afterTextChanged(s: Editable?) {
                        if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                            addTodoView()
                        } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                            removeTodoView(this@apply)
                            if(containerView.childCount == MAX_TODO_COUNT){
                                addTodoView()
                            }
                        }
                        super.afterTextChanged(s)
                    }
                })
            }
            containerView.addView(view)
        }
    }

    private fun removeTodoView(view: CreateTodoView) {
        containerView.removeView(view)
    }

    private fun isTaskEmpty():Boolean = createTaskView.taskEditText.editableText.isNullOrEmpty()

    fun saveTask(callback : (Boolean)->Unit){
        createTask()?.let {
            model.addTask(it){
                //it works
                callback.invoke(true)
            }
        } ?: callback.invoke(false)
    }

    fun createTask(): Task? {
        if(!isTaskEmpty()){
            containerView.run {
                var taskField: String? = null
                var todoList: MutableList<Todo> = mutableListOf()
                for(i in 0 until containerView.childCount){
                    if(i==0){
                        taskField = getChildAt(i).taskEditText.editableText?.toString()
                    }
                    else{
                        if(!getChildAt(i).todoEditText.editableText?.toString().isNullOrEmpty()){
                            todoList.add(Todo(getChildAt(i).todoEditText.editableText.toString()))
                        }
                    }
                }
                return taskField?.let {
                    Task(it,todoList)
                }
            }
        }else{
            return null
        }
    }
    private fun canAddTodos(): Boolean = containerView.childCount < MAX_TODO_COUNT + 1 &&
            !(containerView.getChildAt(containerView.childCount - 1) as NullFieldChecker).hasNullField()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance() = CreateTaskFragment()
    }
}
