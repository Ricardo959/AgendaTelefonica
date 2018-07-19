package com.mydomain.agendatelefonica.database

import com.mydomain.agendatelefonica.model.Contact
import io.realm.Realm

object AgendaDatabase {

    fun copyContacts(contacts: List<Contact>) {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.copyToRealm(contacts)
            it.commitTransaction()
        }
    }

    fun forAllContacts(onSucess: (Contact) -> Unit) {
        Realm.getDefaultInstance().use {
            it.where(Contact::class.java).findAll().forEach {
                onSucess(it)
            }
        }
    }

}