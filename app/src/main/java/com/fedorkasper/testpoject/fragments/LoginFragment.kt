package com.fedorkasper.testpoject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.testpoject.R
import com.example.testpoject.databinding.FragmentLoginBinding
import com.fedorkasper.testpoject.tools.AndroidUtils

class LoginFragment : Fragment() {
    private lateinit var binding :FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewRegistration.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.buttonLogin.setOnClickListener{
            it.findNavController().navigate(R.id.action_global_Social)
        }

        binding.root.setOnClickListener {
            AndroidUtils.hideKeyboard(it)
        }
    }

}