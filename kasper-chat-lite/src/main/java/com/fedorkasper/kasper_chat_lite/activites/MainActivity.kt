package com.fedorkasper.kasper_chat_lite.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.databinding.ActivityMainBinding
import com.fedorkasper.kasper_chat_lite.fragment.social.ListChatsFragment
import com.fedorkasper.kasper_chat_lite.tool.APIManager

class MainActivity : AppCompatActivity(),APIManager.ListenerAPI {
    private lateinit var binding: ActivityMainBinding
    val networkManager = APIManager(this)
    var mineId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun nextActivity() {
        this.binding.root.findNavController().navigate(R.id.action_global_navigation_social, ListChatsFragment.setMineId(mineId))
    }

    override fun login(data: String) {
        TODO("Not yet implemented")
    }

    override fun registration(data: String) {
        TODO("Not yet implemented")
    }

    override fun message(data: String) {
        TODO("Not yet implemented")
    }
    companion object{
        lateinit var instance: MainActivity
        
    }
}