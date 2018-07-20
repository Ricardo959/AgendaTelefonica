package com.mydomain.agendatelefonica.network

import com.mydomain.agendatelefonica.model.Contact
import com.mydomain.agendatelefonica.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AgendaNetwork {
    val AgendaAPI by lazy {
        getRetrofit().create(AgendaAPI::class.java)
    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
                .baseUrl("https://api-agenda-unifor.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun addUser(user: User, onSuccess: () -> Unit, onError: () -> Unit) {
        AgendaAPI.addUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess()
                }, {
                    onError()
                })
    }

    fun login(user: User, onSuccess: (user: User) -> Unit, onError: () -> Unit) {
        AgendaAPI.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    it?.let {
                        user.uid = it.headers().get("Uid")
                        user.client = it.headers().get("Client")
                        user.accessToken = it.headers().get("Access-Token")
                    }

                    if (user.uid == null || user.client == null || user.accessToken == null) {
                        onError()
                    } else {
                        onSuccess(user)
                    }
                }
    }

    fun getContacts(user: User, onSuccess: (contacts: List<Contact>) -> Unit, onError: () -> Unit) {
        AgendaAPI.getContacts(user.uid.toString(),
                user.client.toString(),
                user.accessToken.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let {
                        onSuccess(it)
                    }
                }, {
                    onError()
                })
    }
}