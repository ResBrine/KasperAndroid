package com.fedorkasper.kasper_chat_lite.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.DataModel
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.databinding.FragmentLoginBinding
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.concurrent.thread


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val dataModel:DataModel by activityViewModels   ()

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
        dataModel.loginData.observe(viewLifecycleOwner){
            with(binding) {
                editTextAddress.setText(it[0])
                editTextNameUser.setText(it[1])
            }
        }
        binding.registrationButton.setOnClickListener{
            dataModel.loginData.value = arrayOf(binding.editTextAddress.text.toString(),binding.editTextNameUser.text.toString())
            it.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.confirmButton.setOnClickListener {
            with(binding){
                thread {
                    Log.d("Socket", "Click on button connect in thread")
                    connect(editTextAddress.text.toString().split(':')[0],editTextAddress.text.toString().split(':')[1].toInt(),editTextNameUser.text.toString())
                }
            }
        }
    }
    private fun connect(addressString:String,port:Int,userName:String){

        Log.d("Socket","Start connect")
        val socket = Socket()
        val socketAddress = InetSocketAddress(addressString, port)
        try{
            socket.connect(socketAddress)
            socket.outputStream.write(userName.toByteArray())
            socket.outputStream.write("Hello".toByteArray())
            Log.d("Socket","Connect succeed")
        }catch(e : Exception){
            socket.close()
            Log.d("Socket",e.message.toString())
            //     binding.listView.adapter. .addView("Filed connect" + e.message.toString())
        }
    }
    private fun sendString(){

    }


}