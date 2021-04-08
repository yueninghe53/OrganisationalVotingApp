package com.ut7.organisationalvotingapp.ui.login


import androidx.appcompat.app.AppCompatActivity

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.ut7.organisationalvotingapp.ForgotPassword

import com.ut7.organisationalvotingapp.R
import com.ut7.organisationalvotingapp.VotingActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.ut7.organisationalvotingapp.CreateAnAccount

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val button = findViewById<Button>(R.id.forgotPassword)
        val loginbtn = findViewById<Button>(R.id.login)
        val register_btn = findViewById<Button>(R.id.btn_register)

        button.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener {
            login()
        }

        register_btn.setOnClickListener {
            val intent2 = Intent(this, CreateAnAccount::class.java)
            startActivity(intent2)
        }
    }

    private fun login() {
       if(email.text.toString().isEmpty()) {
           email.error = "Please enter your email address"
           email.requestFocus()
           return
       }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter a valid email address"
            email.requestFocus()
            return
        }
        if(password.text.toString().isEmpty()) {
            password.error = "Please enter your password"
            password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        if(user != null) {
            startActivity(Intent(this, VotingActivity::class.java))
        } else {
            Toast.makeText(baseContext, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
