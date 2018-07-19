package com.mydomain.agendatelefonica.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Contact : RealmObject() {
    @PrimaryKey var id: Long = 0
    var name: String? = null
    var birth: Long? = null
    var email: String? = null
    var phone: String? = null
    var picture: String? = null
}