package com.thegreatsahil.clancomm

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.thegreatsahil.clancomm.databinding.ActivityCreateUserBinding
import kotlin.random.Random

class CreateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUserBinding

    var userAvatar = "profileDefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


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


}