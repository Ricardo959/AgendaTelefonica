package com.mydomain.agendatelefonica.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mydomain.agendatelefonica.R
import com.mydomain.agendatelefonica.business.AgendaBusiness
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)

        configureMain()
    }

    private fun configureMain() {
        loginBtn.setOnClickListener() {
            val username = loginEdt.text.toString()
            AgendaBusiness.searchUser(username, {
                // On Sucess:
                TODO()
            }, {
                // On Error:
                TODO()
            })
        }

        addBtn.setOnClickListener() {
            val extraBundle = Bundle()
            extraBundle.putString("log", loginEdt.text.toString()) // Colocando o Email no 'Bundle'para envio para a tela secundaria
            extraBundle.putString("psw", passwordEdt.text.toString()) // Colocando a senha no 'Bundle'para envio para a tela secundaria

            val intentSideActivity = Intent(this, SignUpActivity::class.java)
            intentSideActivity.putExtras(extraBundle)
            startActivity(intentSideActivity)
        }
    }
}
