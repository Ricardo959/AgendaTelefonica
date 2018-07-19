package com.mydomain.agendatelefonica.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject() {
    @PrimaryKey var uid: String? = null
    var client: String? = null
    var accessToken: String? = null

    var email: String? = null
    var password: String? = null
    var password_confirmation: String? = null
}