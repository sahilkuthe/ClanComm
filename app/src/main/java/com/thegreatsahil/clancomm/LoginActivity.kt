package com.thegreatsahil.clancomm

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.thegreatsahil.clancomm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var userName: EditText
    private lateinit var userPass: EditText
    private lateinit var userEmail: EditText
    private  lateinit var loginBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        userEmail = findViewById(R.id.login_email_textview)
        userPass = findViewById(R.id.login_pass_textview)
        loginBtn = findViewById(R.id.login_login_btn)

        loginBtn.setOnClickListener{
            val email = userEmail.text.toString()
            val password = userPass.text.toString()

            login(email, password)
        }

    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Signup first", Toast.LENGTH_SHORT).show()
                }
            }
    }

//    fun login_login_btn_clicked(view: View){
//
//        val email = binding.loginEmailTextview.text
//        val password = binding.loginPassTextview.text
//
//        if(TextUtils.isEmpty(email)){
//            Toast.makeText(this, "Enter Email !", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if(TextUtils.isEmpty(password)){
//            Toast.makeText(this, "Enter Password !", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        auth.signInWithEmailAndPassword(email.toString(), password.toString())
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
////                    updateUI(user)
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
////                    updateUI(null)
//                }
//            }
//    }
    fun login_signup_btn_clicked(view : View){
        val signupIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(signupIntent)
    }
}