package com.fedorkasper.kasper_chat_lite.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.DataModel
import com.fedorkasper.kasper_chat_lite.databinding.FragmentRegistrationBinding
import com.fedorkasper.kasper_chat_lite.network.networkManager


class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val dataModel:DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener{
            it.findNavController().popBackStack()
        }
        binding.confirmButton.setOnClickListener {
            with(binding){
                if (editTextPassword.text.toString() == editTextPassword2.text.toString())
                    networkManager.API_REGISTRATION(editTextNameUser.text.toString(),editTextPassword.text.toString())
            }
        }
    }

}