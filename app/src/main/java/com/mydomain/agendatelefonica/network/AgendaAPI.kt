package com.mydomain.agendatelefonica.network

import com.mydomain.agendatelefonica.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AgendaAPI {

    @POST("/auth")
    fun addUser(@Body user: User): Observable<Any>

    @POST("/auth/sign_in")
    fun login(@Body emailAndPasswordOnly: User): Observable<Response<User>>

}