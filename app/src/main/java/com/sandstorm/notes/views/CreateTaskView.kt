package com.sandstorm.notes.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.sandstorm.notes.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.view_create_task.view.*

class CreateTaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : LinearLayout(context, attrs, defStyleAttr) , NullFieldChecker {
    override fun hasNullField(): Boolean = taskEditText.editableText.isNullOrEmpty()

}