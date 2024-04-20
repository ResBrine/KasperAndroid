package com.fedorkasper.kasper_chat_lite.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fedorkasper.kasper_chat_lite.social.SocialActivity
import com.fedorkasper.kasper_chat_lite.databinding.ActivityLoginBinding
import com.fedorkasper.kasper_chat_lite.loginActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivity = this
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun nextActivity(){
        val intent = Intent(this, SocialActivity::class.java)
        startActivity(intent)
        finish()
    }
}