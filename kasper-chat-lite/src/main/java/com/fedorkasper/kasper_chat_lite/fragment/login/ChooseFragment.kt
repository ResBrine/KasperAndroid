package com.fedorkasper.kasper_chat_lite.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.activites.MainActivity
import com.fedorkasper.kasper_chat_lite.databinding.FragmentChooseBinding
import com.fedorkasper.kasper_chat_lite.tool.SocketService

class ChooseFragment : Fragment() {
    private lateinit var binding: FragmentChooseBinding
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
            (activity as MainActivity).networkManager.connect(ip,port)

           // ContextCompat.startForegroundService(requireContext(), Intent(requireContext(),SocketService::class.java))
            requireContext().startService(Intent(requireContext(), SocketService::class.java))
                it.findNavController().navigate(R.id.action_chooseFragment_to_loginFragment)

        }
    }
}