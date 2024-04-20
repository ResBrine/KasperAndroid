package com.fedorkasper.kasper_chat_lite.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        with(binding){
            loginButton.setOnClickListener{
                it.findNavController().popBackStack()
            }
            confirmButton.setOnClickListener {
                if(networkManager.socket.isConnected)
                    if (editTextPassword.text.toString() == editTextPassword2.text.toString())
                        networkManager.sendApiRegistration(editTextNameUser.text.toString(),editTextPassword.text.toString())
                    else
                        Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(requireContext(), "Вы не подключены к серверу", Toast.LENGTH_SHORT).show()
            }
        }
    }

}