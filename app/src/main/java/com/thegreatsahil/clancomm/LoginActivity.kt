package com.thegreatsahil.clancomm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login_login_btn_clicked(view: View){

    }
    fun login_signup_btn_clicked(view : View){
        val signupIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(signupIntent)
    }
}