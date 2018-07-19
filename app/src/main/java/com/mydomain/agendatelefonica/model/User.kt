package com.mydomain.agendatelefonica.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject() {
    @PrimaryKey var id: Long = 0
    var email: String? = null
    var password: String? = null
}