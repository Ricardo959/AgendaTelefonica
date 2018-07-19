package com.mydomain.agendatelefonica.network

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

    fun login(emailAndPasswordOnly: User, onSuccess: (user: User) -> Unit, onError: () -> Unit) {
        AgendaAPI.login(emailAndPasswordOnly)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    val apiResponse = response?.body() //APIResponse
                    val usuario = apiResponse.data

                    usuario.uid = response?.headers().get("asdas")
                    usuario.client = response?.headers().get("asdasd")
                    usuario.accessToken = response?.headers().get("asdasd")

                    onSuccess(usuario)

                }, {
                    onError()
                })
    }
}