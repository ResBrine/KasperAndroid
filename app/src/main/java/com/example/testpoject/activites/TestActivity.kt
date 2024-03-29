package com.example.testpoject.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testpoject.R
import com.example.testpoject.databinding.ActivityTestBinding
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class TestActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTestBinding
    private var socket: Socket? = null
    private var addressString : String = "192.168.1.208"
    private var port : Int = 8301
    private var username : String = "MobileApp"
    private lateinit var socketAddress: InetSocketAddress
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConnect.setOnClickListener {
            thread {
                Log.d("Socket", "Click on button connect in thread")
                addressString = binding.editTextIp.text.split(':')[0]
                port = binding.editTextIp.text.split(':')[1].toInt()
                socketAddress = InetSocketAddress(addressString, port)
                username = binding.editTextUserName.text.toString()
                connect()
            }
        }

        binding.btnSend.setOnClickListener {
            thread {
                socket!!.outputStream.write(binding.editTextSend.text.toString().toByteArray())
            }
        }


    }

    private fun connect(){

        Log.d("Socket","Start connect")
        socket = Socket()
        socketAddress = InetSocketAddress(addressString, port)
        try{
            socket!!.connect(socketAddress)
            socket!!.outputStream.write(username.toByteArray())
            Log.d("Socket","Connect succeed")
        }catch(e : Exception){
            socket!!.close()
            Log.d("Socket",e.message.toString())
        //     binding.listView.adapter. .addView("Filed connect" + e.message.toString())
        }
    }
}