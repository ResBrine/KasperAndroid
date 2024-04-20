package com.fedorkasper.kasper_chat_lite.social

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.databinding.ActivitySocialBinding

class SocialActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySocialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocialBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}