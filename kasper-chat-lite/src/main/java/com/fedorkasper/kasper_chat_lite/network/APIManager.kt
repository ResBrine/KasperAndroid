package com.fedorkasper.kasper_chat_lite.network

import android.util.Log
import com.fedorkasper.kasper_chat_lite.loginActivity
import com.fedorkasper.kasper_chat_lite.social.ListChatsFragment
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.concurrent.thread
val networkManager = APIManager()

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
    fun sendApiLogin(userName:String, password:String){
       sendString(
            "login\n" +
                    "{\n" +
                    "userName="+userName+"\n" +
                    "password="+password+"\n" +
                    "}"
        )
    }
    fun sendApiRegistration(userName:String, password:String){
        sendString(
            "registration\n" +
                    "{\n" +
                    "userName="+userName+"\n" +
                    "password="+password+"\n" +
                    "}"
        )
    }
    private fun sendString(text:String){
        thread {
            try {
                if (socket.isConnected)
                    socket.outputStream.write(text.toByteArray())
                else{
                    Log.e("Socket", "Не подключен Socket")
                }
            } catch (e: Exception) {

                Log.e("Socket", "Exception " + e.message.toString())

            }
        }

    }

    fun recognize(apiString: String)
    {
        if (apiString.split("\n")[0] == "login")
            loginActivity.nextActivity()
        if (apiString.split("\n")[0] == "friends")
            ListChatsFragment.newInstance(apiString,"pon")
        Log.d("Socket-Get", apiString)
    }

}
