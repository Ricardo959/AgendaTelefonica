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

    fun checkUser(user: User, onSuccess: () -> Unit, onError: () -> Unit) {
        Realm.getDefaultInstance().use {
            getUser {
                if (it != null) {
                    if (it.email == user.email && it.password == user.password) {
                        onSuccess()
                    } else {
                        onError()
                    }
                } else {
                    onError()
                }
            }
        }
    }

    fun getUser(onSuccess: (user: User?) -> Unit) {
        Realm.getDefaultInstance().use {
            onSuccess(it.where(User::class.java).findFirst())
        }
    }

    fun copyContacts(contacts: List<Contact>) {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.copyToRealmOrUpdate(contacts)
            it.commitTransaction()
        }
    }

    fun forAllContacts(onSuccess: (contact: Contact) -> Unit) {
        Realm.getDefaultInstance().use {
            it.where(Contact::class.java).findAll().forEach {
                onSuccess(it)
            }
        }
    }

    fun clearDatabase() {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.deleteAll()
            it.commitTransaction()
        }
    }
}