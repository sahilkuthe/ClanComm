package com.thegreatsahil.clancomm

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
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
import com.thegreatsahil.clancomm.databinding.ActivityCreateUserBinding
import kotlin.random.Random


class CreateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUserBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var userName: EditText
    private lateinit var userPass: EditText
    private lateinit var userEmail: EditText
    private lateinit var createUserBtn: Button

    var userAvatar = "profileDefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"



    private fun reload() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        userName = findViewById(R.id.createUserName)
        userEmail = findViewById(R.id.createUserEmail)
        userPass = findViewById(R.id.createUserPassword)
        createUserBtn = findViewById(R.id.createUserBtn)


        createUserBtn.setOnClickListener{
            val email = userEmail.text.toString()
            val password = userPass.text.toString()
            val name = userName.text.toString()


            signup(name, email, password, userAvatar)
        }



    }

    private fun signup(name: String, email: String, password: String, userAvatar: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@CreateUserActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@CreateUserActivity, "Signup failed try again.", Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun createUserAvatarImgClicked(view: View){
        val color = Random.nextInt(2)
        val avatar = Random.nextInt(28)

        if(color == 0){
            userAvatar = "light$avatar"
        }else{
            userAvatar = "dark$avatar"
        }
        val resourceId = resources.getIdentifier(userAvatar, "drawable", this.packageName)
        binding.createUserAvatar.setImageResource(resourceId)
    }
    fun generateBGColorClicked(view: View){
        val r = Random.nextInt(255)
        val g = Random.nextInt(255)
        val b = Random.nextInt(255)

        binding.createUserAvatar.setBackgroundColor(Color.rgb(r, g, b))

        //to set the values of r, g, b between 0 to 1:
        val savedR = r.toDouble() / 255
        val savedG = g.toDouble() / 255
        val savedB = b.toDouble() / 255

        avatarColor = "[$savedR, $savedG, $savedB, 1]"
    }

//    fun createUserBtnClicked(view: View){
//
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
//        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
////                    updateUI(user)
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()
////                    updateUI(null)
//                }
//            }
//    }


}