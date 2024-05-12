package com.fedorkasper.testpoject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.testpoject.R
import com.example.testpoject.databinding.FragmentRegistrationBinding
import com.fedorkasper.testpoject.tools.AndroidUtils

class RegistrationFragment : Fragment() {
    private lateinit var binding : FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewLogin.setOnClickListener{
            it.findNavController().popBackStack()
        }
        binding.button2.setOnClickListener{
            it.findNavController().navigate(R.id.action_global_Social)
        }
        binding.root.setOnClickListener {
            AndroidUtils.hideKeyboard(it)
        }
    }
}