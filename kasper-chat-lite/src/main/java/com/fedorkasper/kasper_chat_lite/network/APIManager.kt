package com.fedorkasper.kasper_chat_lite.network

import android.util.Log
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.concurrent.thread
val networkManager = APIManager()
interface APIRequest{
    fun newMessagePerson(apiString: String)
    fun newMessageRoom(apiString: String)
}
class APIManager {

    val socket = Socket()
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
    fun send_API_LOGIN(userName:String, password:String){
       send_String(
            "login\n" +
                    "{\n" +
                    "userName="+userName+"\n" +
                    "password="+password+"\n" +
                    "}"
        )
    }
    fun send_API_REGISTRATION(userName:String, password:String){
        send_String(
            "registration\n" +
                    "{\n" +
                    "userName="+userName+"\n" +
                    "password="+password+"\n" +
                    "}"
        )
    }
    private fun send_String(text:String){
        thread {
            try {
                socket.outputStream.write(text.toByteArray())
            } catch (e: Exception) {
                socket.close()
                Log.d("Socket", "Exception " + e.message.toString())

            }
        }

    }

    fun recognize(apiString: String)
    {
        Log.d("Socket-Get", apiString)

    }
}
