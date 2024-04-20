package com.fedorkasper.kasper_chat_lite.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.DataModel
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.databinding.FragmentChooseBinding
import com.fedorkasper.kasper_chat_lite.network.networkManager
import com.fedorkasper.kasper_chat_lite.service.SocketService

class ChooseFragment : Fragment() {
    private lateinit var binding: FragmentChooseBinding
    private val dataModel:DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toConnectButton.setOnClickListener {
                val port = binding.editTextPort.text.toString().toInt()
                val ip = binding.editTextIp.text.toString()
                networkManager.connect(ip,port)

           // ContextCompat.startForegroundService(requireContext(), Intent(requireContext(),SocketService::class.java))
            requireContext().startService(Intent(requireContext(),SocketService::class.java))
                it.findNavController().navigate(R.id.action_chooseFragment_to_loginFragment)

        }
    }
}