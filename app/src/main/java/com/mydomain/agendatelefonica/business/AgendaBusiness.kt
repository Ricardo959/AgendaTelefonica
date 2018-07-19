package com.mydomain.agendatelefonica.business

import com.mydomain.agendatelefonica.database.AgendaDatabase
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
            // On Sucess:
            onSuccess()
        }, {
            // On Error:
            onError()
        })
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
        // Create user:
        val user = User()
        user.email = email
        user.password = password

        // Send to retrofit:
        AgendaNetwork.login(user, {
            // On Sucess:
            AgendaDatabase.copyOrUpdateUser(it)
            onSuccess()

            } else {
                onError()
            }
        }, {
            // On Error:
            onError()
        })

    }
}