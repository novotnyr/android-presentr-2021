package com.github.novotnyr.presentr

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var userListViewAdapter: ArrayAdapter<User>

    private val userViewModel: UserViewModel by viewModels()

    private val periodicHandler = Handler(Looper.getMainLooper())

    private val periodicRefreshTask = object : Runnable {
        override fun run() {
            userViewModel.refresh()
            periodicHandler.postDelayed(this, 60*1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userListViewAdapter = ArrayAdapter<User>(this, android.R.layout.simple_list_item_1)

        val userListView: ListView = findViewById(R.id.userListView)
        userListView.adapter = userListViewAdapter

        userViewModel.refresh()
        userViewModel.users.observe(this) {
            userListViewAdapter.clear()
            userListViewAdapter.addAll(it)
        }
        periodicHandler.postDelayed(periodicRefreshTask, 60*1000)
    }

    fun onFloatingActionButtonClick(view: View) {
        userViewModel.login(User("johndoe"))
        Snackbar.make(view, "Používateľ je v miestnosti!", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_item_refresh -> {
                userViewModel.refresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}