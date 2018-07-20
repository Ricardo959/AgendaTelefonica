package com.mydomain.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mydomain.agendatelefonica.R
import com.mydomain.agendatelefonica.adapter.ContactAdapter
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = ContactAdapter()
        recyclerView.setHasFixedSize(true)
    }

}
