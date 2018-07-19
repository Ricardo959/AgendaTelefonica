package com.mydomain.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mydomain.agendatelefonica.R
import kotlinx.android.synthetic.main.activity_main.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        configureSignUp()
    }

    private fun configureSignUp() {
        val email = intent.getStringExtra("log") // Recebendo a matricula da tela anterior
        val password = intent.getStringExtra("psw") // Recebendo a senha da tela anterior

        loginEdt.setText(email)
        passwordEdt.setText(password)
    }
}
