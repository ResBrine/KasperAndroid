package com.fedorkasper.kasper_chat_lite.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.DataModel
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.createNotification
import com.fedorkasper.kasper_chat_lite.databinding.FragmentLoginBinding
import com.fedorkasper.kasper_chat_lite.network.networkManager


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val dataModel:DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)

            createNotification(requireContext(),"Kasper Chat", "Hello")
        }

        binding.confirmButton.setOnClickListener {
            with(binding){

                    networkManager.API_LOGIN(editTextNameUser.text.toString(),editTextPassword.text.toString())

            }
        }
    }






}