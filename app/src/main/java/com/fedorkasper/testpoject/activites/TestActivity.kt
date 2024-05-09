package com.fedorkasper.testpoject.activites

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import com.example.testpoject.databinding.ActivityTestBinding
import com.fedorkasper.testpoject.message.MessageAdapter
import com.fedorkasper.testpoject.message.MessageViewModal
import com.fedorkasper.testpoject.constants.userName
import java.net.InetSocketAddress
import java.net.Socket
import java.util.Calendar
import kotlin.concurrent.thread

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    private var socket: Socket? = null
    private var addressString : String = "192.168.179.188"
    private var port : Int = 8301
    private lateinit var socketAddress: InetSocketAddress
    private val messageViewModal: MessageViewModal by viewModels()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = MessageAdapter(object : MessageAdapter.Listener {
            override fun reading() {
            }

        })
        binding.editTextIp.setText("$addressString:$port")
        binding.recyclerViewItemChat.adapter = adapter // Передаю адаптер нашему RecyclerView,

        messageViewModal.data.observe(this){  // observe - следит за изменениями
            // Если произошли изменения в postViewModel.data,
            // то будет происходить действие ниже
            adapter.submitList(it) // ЛОЛОЛ ТРУ ляля Завтра грабим короля
        }

        binding.btnConnect.setOnClickListener {
            userName = binding.editTextUserName.text.toString()
            //messageViewModal.addMessage(userName,getDeviceIpAddress("NB-ldeaPad5-FvK"),Calendar.getInstance().time)

            thread {
                Log.d("Socket", "Click on button connect in thread")
                addressString = binding.editTextIp.text.split(':')[0]
                port = binding.editTextIp.text.split(':')[1].toInt()
                socketAddress = InetSocketAddress(addressString, port)
                connect()
            }
        }

        binding.btnSend.setOnClickListener {
            messageViewModal.addMessage(userName,binding.editTextSend.text.toString(),Calendar.getInstance().time)
            binding.recyclerViewItemChat.scrollToPosition(binding.recyclerViewItemChat.size)
            thread {
                socket!!.outputStream.write(binding.editTextSend.text.toString().toByteArray())
            }
            binding.editTextSend.text.clear()

        }




    }

    private fun connect(){

        Log.d("Socket","Start connect")
        socket = Socket()
        socketAddress = InetSocketAddress(addressString, port)
        try{
            socket!!.connect(socketAddress)
            socket!!.outputStream.write(userName.toByteArray())
            Log.d("Socket","Connect succeed")
        }catch(e : Exception){
            socket!!.close()
            Log.d("Socket",e.message.toString())
        //     binding.listView.adapter. .addView("Filed connect" + e.message.toString())
        }
    }
}