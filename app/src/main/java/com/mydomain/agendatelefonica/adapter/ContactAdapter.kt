package com.mydomain.agendatelefonica.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mydomain.agendatelefonica.R
import com.mydomain.agendatelefonica.model.ContactViewHolder

class ContactAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    val contactNameList: List<String> = listOf("Pessoa A", "Pessoa B", "Pessoa C", "Pessoa A", "Pessoa B", "Pessoa C", "Pessoa A", "Pessoa B", "Pessoa C", "Pessoa A", "Pessoa B", "Pessoa C")
    val contactPhoneList: List<String> = listOf("Telefone A", "Telefone B", "Telefone C", "Telefone A", "Telefone B", "Telefone C", "Telefone A", "Telefone B", "Telefone C", "Telefone A", "Telefone B", "Telefone C")
    val contactEmailList: List<String> = listOf("Email A", "Email B", "Email C", "Email A", "Email B", "Email C", "Email A", "Email B", "Email C", "Email A", "Email B", "Email C")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_contact, parent, false)

        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = contactNameList.size

    override fun onBindViewHolder(holder: ContactViewHolder, pos: Int) {
        holder.bind(contactNameList[pos], contactPhoneList[pos], contactEmailList[pos])
    }

}