package com.mydomain.agendatelefonica.view

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.TextView
import com.mydomain.agendatelefonica.R
import com.mydomain.agendatelefonica.business.AgendaBusiness
import kotlinx.android.synthetic.main.activity_sign_up.*

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

        addBtn.setOnClickListener {
            if (passwordEdt.text.toString() != passwordConfEdt.text.toString()) {
                displayErrorMessage(getString(R.string.passwordError))
            } else if (passwordEdt.text.length < 8) {
                displayErrorMessage(getString(R.string.passwordError2))
            } else {
                // Create Account
                displayMessage(getString(R.string.wait))

                AgendaBusiness.addUser(loginEdt.text.toString(), passwordEdt.text.toString(), {
                    // On Sucess:
                    val intentContactList = Intent(this, ContactListActivity::class.java)
                    startActivity(intentContactList)
                }, {
                    // On Error:
                    displayErrorMessage(getString(R.string.addUserError))
                })
            }
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
