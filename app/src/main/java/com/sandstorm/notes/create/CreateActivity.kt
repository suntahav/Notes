package com.sandstorm.notes.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sandstorm.notes.R
import com.sandstorm.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        intent.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY).run {
            createTitle.text = when {
                this==NavigationActivity.FRAGMENT_VALUE_TASK -> "Add Task Activity"
                this==NavigationActivity.FRAGMENT_VALUE_NOTE -> "Add Note Activity"
                else -> "Something is Wrong"
            }
        }

    }
}
