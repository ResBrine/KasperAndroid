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
        dataModel.loginData.observe(viewLifecycleOwner){
            with(binding) {
                editTextAddress.setText(it[0])
                editTextNameUser.setText(it[1])
            }
        }
        binding.loginButton.setOnClickListener{
            dataModel.loginData.value = arrayOf(binding.editTextAddress.text.toString(),binding.editTextNameUser.text.toString())
            it.findNavController().popBackStack()
        }
    }

}