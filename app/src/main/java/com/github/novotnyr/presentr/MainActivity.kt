package com.github.novotnyr.presentr

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var userListViewAdapter: ArrayAdapter<User>

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userListViewAdapter = ArrayAdapter<User>(this, android.R.layout.simple_list_item_1)

        val userListView: ListView = findViewById(R.id.userListView)
        userListView.adapter = userListViewAdapter

        userViewModel.users.observe(this) {
            userListViewAdapter.clear()
            userListViewAdapter.addAll(it)
        }
    }

    fun onFloatingActionButtonClick(view: View) {
        userViewModel.login(User("johndoe"))
        Snackbar.make(view, "Používateľ je v miestnosti!", Snackbar.LENGTH_SHORT)
            .show()
    }
}