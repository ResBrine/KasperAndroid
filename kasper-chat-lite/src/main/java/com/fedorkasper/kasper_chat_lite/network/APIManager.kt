package com.fedorkasper.kasper_chat_lite.network

import android.util.Log
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.concurrent.thread
val apiManager = APIManager()
class APIManager {

    private val socket = Socket()
    fun connect(host: String,port: Int):String{

        val socketAddress = InetSocketAddress(host, port)
        return try{
            thread {
                socket.connect(socketAddress)
                Log.d("Socket","Успешное подключение")
            }
            toString()
        }catch(e : Exception){
            thread {
                socket.close()
            }
            Log.d("Socket",e.message.toString())
            e.message.toString()
        }
    }
    fun API_LOGIN(userName:String,password:String){
       sendString(
            "login\n" +
                    "{\n" +
                    "userName="+userName+"\n" +
                    "password="+password+"\n" +
                    "}"
        )
    }
    fun API_REGISTRATION(userName:String,password:String){
        sendString(
            "registration\n" +
                    "{\n" +
                    "userName="+userName+"\n" +
                    "password="+password+"\n" +
                    "}"
        )
    }
    fun sendString(text:String){
        thread {
            try {
                Log.d("Socket", "Отправка сообщения:$text")
                socket.outputStream.write(text.toByteArray())
            } catch (e: Exception) {
                socket.close()
                Log.d("Socket", "Exception " + e.message.toString())

            }
        }

    }
}
