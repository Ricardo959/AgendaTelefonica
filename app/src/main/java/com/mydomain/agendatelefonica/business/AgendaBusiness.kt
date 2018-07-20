package com.mydomain.agendatelefonica.business

import android.util.Log
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
            // Login to retrofit
            AgendaNetwork.login(user, {
                // On Success:
                AgendaDatabase.clearDatabase()
                AgendaDatabase.copyOrUpdateUser(it)
                onSuccess()
            }, {
                Log.d("TAG", "Login failed")
                onError()
            })
        }, {
            Log.d("TAG", "Add User request failed")
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
            Log.d("TAG", "User ${email} has been located in database")
            updateContacts({
                Log.d("TAG", "Contacts updated successfully")
                onSuccess()
            }, {
                Log.d("TAG", "Contacts updated failed")
                onError()
            })
        }, {
            // New user.
            Log.d("TAG", "User ${email} has not been located in database")
            // Login to retrofit:
            AgendaNetwork.login(user, {
                // On Success:
                Log.d("TAG", "User ${email} has been found on network")
                AgendaDatabase.clearDatabase()
                AgendaDatabase.copyOrUpdateUser(it)

                updateContacts({
                    Log.d("TAG", "Contacts updated successfully")
                    onSuccess()
                }, {
                    Log.d("TAG", "Contacts update failed")
                    onError()
                })
            }, {
                Log.d("TAG", "User ${email} has not been found online")
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

    fun getNamePhoneEmail(): Triple<List<String>, List<String>, List<String>> {

        val names: MutableList<String> = mutableListOf()
        val phones: MutableList<String> = mutableListOf()
        val emails: MutableList<String> = mutableListOf()

        AgendaDatabase.forAllContacts {
            it.name?.let {
                names.add(it)
            }

            it.phone?.let {
                phones.add(it)
            }

            it.email?.let {
                emails.add(it)
            }
        }

        return Triple(names, phones, emails)
    }

    fun addContact(name: String,
                   email: String,
                   phone: String,
                   birth: Long,
                   picture: String,
                   onSuccess: () -> Unit,
                   onError: () -> Unit) {

        // Create contact:
        val contact = Contact()
        contact.name = name
        contact.email = email
        contact.phone = phone
        contact.birth = birth
        contact.picture = picture

        AgendaDatabase.getUser {
            it?.let {

                // Send to retrofit:
                Log.d("TAG", "Sending contact ${contact.name} to network")
                AgendaNetwork.addContact(it, contact, {
                    // On Success:
                    // Update Contacts
                    updateContacts({
                        onSuccess()
                    }, {
                        Log.d("TAG", "Failed to ubdate database")
                        onError()
                    })
                }, {
                    Log.d("TAG", "Failed to send contact")
                    onError()
                })

            }
        }
    }
}