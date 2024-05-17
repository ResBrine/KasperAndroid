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
import com.fedorkasper.kasper_chat_lite.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding

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
                if ((activity as MainActivity).networkManager.socket.isConnected)
                    if (editTextPassword.text.toString() == editTextPassword2.text.toString()){
                        (activity as MainActivity).networkManager.sendApiRegistration(
                            editTextNameUser.text.toString(),
                            editTextPassword.text.toString()
                        )
                        it.findNavController().navigate(R.id.action_global_navigation_social)

                    }else
                        Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(requireContext(), "Вы не подключены к серверу", Toast.LENGTH_SHORT).show()
            }
        }
    }

}