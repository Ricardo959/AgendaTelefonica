package com.mydomain.agendatelefonica.business

import com.mydomain.agendatelefonica.database.AgendaDatabase
import com.mydomain.agendatelefonica.model.Contact
import com.mydomain.agendatelefonica.model.User
import com.mydomain.agendatelefonica.network.AgendaNetwork

object AgendaBusiness {

    fun addUser(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
        // Create user:
        val user = User()
        user.email = email
        user.password = password
        user.password_confirmation = password

        // Send to retrofit:
        AgendaNetwork.addUser(user, {
            // On Success:
            // Login to retrofit:
            AgendaNetwork.login(user, {
                // On Success:
                AgendaDatabase.clearDatabase()
                AgendaDatabase.copyOrUpdateUser(it)
                onSuccess()
            }, {
                onError()
            })
        }, {
            onError()
        })
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
        // Create user:
        val user = User()
        user.email = email
        user.password = password
        user.password_confirmation = password

        AgendaDatabase.checkUser(user, {
            // Same user.
            updateContacts({
                onSuccess()
            }, {
                onError()
            })
        }, {
            // New user.
            // Login to retrofit:
            AgendaNetwork.login(user, {
                // On Success:
                AgendaDatabase.clearDatabase()
                AgendaDatabase.copyOrUpdateUser(it)

                updateContacts({
                    onSuccess()
                }, {
                    onError()
                })
            }, {
                onError()
            })
        })
    }

    fun updateContacts(onSuccess: () -> Unit, onError: () -> Unit) {
        AgendaDatabase.getUser {
            it?.let {
                AgendaNetwork.getContacts(it, {
                    // On Success:
                    AgendaDatabase.copyContacts(it)
                    onSuccess()
                }, {
                    onError()
                })
            }
        }
    }
}