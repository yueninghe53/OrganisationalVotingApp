package com.ut7.organisationalvotingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ut7.organisationalvotingapp.R





class UserProfileActivity : AppCompatActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_user_profile)
    }
}