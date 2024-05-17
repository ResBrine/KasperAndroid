package com.fedorkasper.kasper_chat_lite.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.activites.MainActivity
import com.fedorkasper.kasper_chat_lite.databinding.FragmentLoginBinding
import com.fedorkasper.kasper_chat_lite.tool.createNotification


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
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
                    if((activity as MainActivity).networkManager.socket.isConnected) {
                        (activity as MainActivity).networkManager.sendApiLogin(
                            editTextNameUser.text.toString(),
                            editTextPassword.text.toString()
                        )
                        it.findNavController().navigate(R.id.action_global_navigation_social)
                    }else
                        Toast.makeText(requireContext(), "Вы не подключены к серверу", Toast.LENGTH_SHORT).show()
            }
        }
    }






}