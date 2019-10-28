package com.sandstorm.notes.navigation

import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sandstorm.notes.R
import com.sandstorm.notes.notes.NotesListFragment
import com.sandstorm.notes.tasks.TasksListFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_notes -> {
                    replaceFragment(NotesListFragment.newInstance())
                    true
                }
                R.id.navigation_tasks -> {
                    replaceFragment(TasksListFragment.newInstance())
                    true
                }
                else -> false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        replaceFragment(TasksListFragment.newInstance())
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder,fragment)
            .commit()
    }

}
