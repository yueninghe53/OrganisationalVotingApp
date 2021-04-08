package com.ut7.organisationalvotingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btn_submit.setOnClickListener {
            val email: String = forgot_email.text.toString().trim { it <= ' ' }
            if(email.isEmpty()) {
                Toast.makeText(this@ForgotPassword,
                    "Please enter your email",
                    Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotPassword,
                                "Email sent successfully to reset your password!",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                    } else {
                        Toast.makeText(
                            this@ForgotPassword,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        }
                }
            }
        }
    }
}