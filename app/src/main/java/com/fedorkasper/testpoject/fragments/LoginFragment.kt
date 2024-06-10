package com.fedorkasper.testpoject.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.testpoject.R
import com.example.testpoject.databinding.FragmentLoginBinding
import com.fedorkasper.testpoject.activites.MainActivity
import com.fedorkasper.testpoject.tools.AndroidUtils
import com.fedorkasper.testpoject.tools.SocketService
import kotlin.concurrent.thread

class LoginFragment : Fragment() {
    private lateinit var binding :FragmentLoginBinding
    companion object{
        lateinit var instance: LoginFragment
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        instance = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewRegistration.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.buttonLogin.setOnClickListener{
            with(MainActivity.instance.networkManager){
                with(binding) {
                    if (!socket.isConnected)
                    {
                        connect(binding.editTextHost.text.toString(), 8301)
                        Log.d("Socket", MainActivity.instance.toString() )

                        MainActivity.instance.startService(
                            Intent(
                                MainActivity.instance,
                                SocketService::class.java
                            )
                        )
                        Log.d("Socket", "endStart" )

                    }
                    else{
                        sendApiLogin(editTextText.text.toString(), editTextTextPassword.text.toString())
                    }
                }
            }
        }

        binding.root.setOnClickListener {
            AndroidUtils.hideKeyboard(it)
        }
    }

}