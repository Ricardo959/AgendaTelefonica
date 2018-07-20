package com.mydomain.agendatelefonica.network

import com.mydomain.agendatelefonica.model.Contact
import com.mydomain.agendatelefonica.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface AgendaAPI {

    @POST("/auth")
    fun addUser(@Body user: User): Observable<Any>

    @POST("/auth/sign_in")
    fun login(@Body emailAndPasswordOnly: User): Observable<Response<User>>

    @GET(" /contacts")
    fun getContacts(@Header("Uid") uid: String,
                    @Header("Client") client: String,
                    @Header("Access-Token") accessToken: String): Observable<List<Contact>>

    @POST(" /contacts")
    fun addContact(@Header("Uid") uid: String,
                   @Header("Client") client: String,
                   @Header("Access-Token") accessToken: String,
                   @Body contact: Contact): Observable<Contact>
}