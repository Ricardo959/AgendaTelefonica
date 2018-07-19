package com.mydomain.agendatelefonica.database

import com.mydomain.agendatelefonica.model.Contact
import com.mydomain.agendatelefonica.model.User
import io.realm.Realm

object AgendaDatabase {
    fun copyOrUpdateUser(user: User) {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.copyToRealmOrUpdate(user)
            it.commitTransaction()
        }
    }

    fun copyContacts(contacts: List<Contact>) {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.copyToRealm(contacts)
            it.commitTransaction()
        }
    }

    fun forAllContacts(onSuccess: (Contact) -> Unit) {
        Realm.getDefaultInstance().use {
            it.where(Contact::class.java).findAll().forEach {
                onSuccess(it)
            }
        }
    }

}