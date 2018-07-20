package com.mydomain.agendatelefonica.view

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.TextView
import com.mydomain.agendatelefonica.R
import com.mydomain.agendatelefonica.business.AgendaBusiness
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        setup()
    }

    private fun setup() {

        addBtn.setOnClickListener {
            if (nameEdt.text.length < 2 || emailEdt.text.length < 4 || phoneEdt.text.length < 4) {
                displayErrorMessage(getString(R.string.invalidContact))

            } else {
                displayMessage(getString(R.string.wait))

                AgendaBusiness.addContact(nameEdt.text.toString(),
                        emailEdt.text.toString(),
                        phoneEdt.text.toString(),
                        1970,
                        pictureEdt.text.toString(), {
                    // On Success:
                    val intentContactList = Intent(this, ContactListActivity::class.java)
                    startActivity(intentContactList)
                }, {
                    // On Error:
                    displayErrorMessage(getString(R.string.contactExistent))
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
