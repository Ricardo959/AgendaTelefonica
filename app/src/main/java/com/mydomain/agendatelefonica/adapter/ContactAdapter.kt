package com.mydomain.agendatelefonica.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mydomain.agendatelefonica.R
import com.mydomain.agendatelefonica.model.ContactViewHolder

class ContactAdapter(contactNameList: List<String>,
                     contactPhoneList: List<String>,
                     contactEmailList: List<String>) : RecyclerView.Adapter<ContactViewHolder>() {

    private val names = contactNameList
    private val phones = contactPhoneList
    private val emails = contactEmailList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_contact, parent, false)

        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = names.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(names[position], phones[position], emails[position])
    }

}