package com.mydomain.agendatelefonica.model

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_contact.view.*

class ContactViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun bind(name: String, phone: String, email: String) {
        with(view) {
            nameTxt.text = name
            phoneTxt.text = phone
            emailTxt.text = email
        }
    }

}