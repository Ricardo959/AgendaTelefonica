package com.mydomain.agendatelefonica.view

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.TextView
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
        loginBtn.setOnClickListener {
            displayMessage(getString(R.string.wait))

            AgendaBusiness.login(loginEdt.text.toString(), passwordEdt.text.toString(), {
                // On Sucess:
                TODO("Inflate new activity")
            }, {
                // On Error:
                displayErrorMessage(getString(R.string.loginError))
            })
        }

        addBtn.setOnClickListener() {
            val extraBundle = Bundle()
            extraBundle.putString("log", loginEdt.text.toString()) // Colocando o Email no 'Bundle'para envio para a tela secundaria
            extraBundle.putString("psw", passwordEdt.text.toString()) // Colocando a senha no 'Bundle'para envio para a tela secundaria

            val intentSignUp = Intent(this, SignUpActivity::class.java)
            intentSignUp.putExtras(extraBundle)
            startActivity(intentSignUp)
        }
    }

    private fun displayErrorMessage(message: String) {
        val snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG)

        snackbar.view.setBackgroundColor(Color.RED)
        snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
                .setTextColor(Color.WHITE)

        snackbar.show()
    }

    private fun displayMessage(message: String) {
        val snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG)

        snackbar.view.setBackgroundColor(Color.parseColor("#2095f2"))
        snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
                .setTextColor(Color.WHITE)

        snackbar.show()
    }
}
