package com.github.novotnyr.presentr

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var userListViewAdapter: ArrayAdapter<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userListViewAdapter = ArrayAdapter<User>(this, android.R.layout.simple_list_item_1)

        val userListView: ListView = findViewById(R.id.userListView)
        userListView.adapter = userListViewAdapter
    }

    fun onFloatingActionButtonClick(view: View) {}
}