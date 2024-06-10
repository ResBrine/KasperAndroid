package com.fedorkasper.testpoject.activites

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.testpoject.R
import com.example.testpoject.databinding.ActivityMainBinding
import com.fedorkasper.testpoject.fragments.LoginFragment
import com.fedorkasper.testpoject.itemchat.ItemChatViewModal
import com.fedorkasper.testpoject.tools.APIManager
import com.fedorkasper.testpoject.tools.iUser
import com.fedorkasper.testpoject.tools.userName

class MainActivity : AppCompatActivity(), APIManager.ListenerAPI {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:ItemChatViewModal by viewModels()
    val networkManager = APIManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    companion object{
        lateinit var instance: MainActivity
    }





    override fun getlogindata(data: APIManager.FullDataClient) {
        userName = data.userName
        LoginFragment.instance.view?.findNavController()?.navigate(R.id.action_global_Social)
    }

    override fun message(data: APIManager.Message) {
        viewModel.addMessage(data)
    }
}