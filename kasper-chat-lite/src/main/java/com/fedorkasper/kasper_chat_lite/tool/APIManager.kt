package com.fedorkasper.kasper_chat_lite.tool

import android.util.Log
import com.google.gson.Gson
import java.net.InetSocketAddress
import java.net.Socket
import java.time.LocalDateTime
import kotlin.concurrent.thread


class APIManager(private val listenerAPI: ListenerAPI) {
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
       sendString(createLoginData(false, userName, password))
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
//        if (apiString.split("\n")[0] == "login")
//            listenerAPI.login(apiString)
//        if (apiString.split("\n")[0] == "registration")
//            listenerAPI.registration(apiString)
//        if (apiString.split("\n")[0] == "message")
//            listenerAPI.message(apiString)
//        if (apiString.split("\n")[0] == "friends")
//            ListChatsFragment.newInstance(apiString,"pon")
        Log.d("Socket-Get", apiString)
    }

    interface ListenerAPI{
        fun login(data:String)
        fun registration(data:String)
        fun message(data:String)

    }
    data class Message(
        var typeMessage: Boolean = true,
        var idChat: Long,
        var idUser: Long,
        var nameUser: String,
        var message: String,
        var date: LocalDateTime
    )

    data class AuthenticationDataClient(
        var typeAuthenticationDataClient: Boolean = true,
        var isNew: Boolean,
        var userName: String,
        var password: String
    )

    data class FullDataClient(
        var typeFullDataClient: Boolean = true,
        var userName: String,
        var password: String,
        var friends: String,
        var rooms: String
    )

    data class SimpleAnswer(
        var typeSimpleAnswer: Boolean = true,
        var header: String,
        var value: String
    )

    companion object {
        fun createSimpleAnswer(header: String, value: String): String {
            return Gson().toJson(SimpleAnswer(header = header, value = value))
        }

        fun createMessage(message: Message): String {
            return Gson().toJson(message)
        }

        fun createMessage(idChat: Long, idUser: Long, nameUser: String, message: String, date: LocalDateTime): String {
            return Gson().toJson(Message(idChat = idChat, idUser = idUser, nameUser = nameUser, message = message, date = date))
        }

        fun getMessage(data: String): Message {
            return Gson().fromJson(data, Message::class.java)
        }

        fun getSimpleAnswer(data: String): SimpleAnswer {
            return Gson().fromJson(data, SimpleAnswer::class.java)
        }
        fun createLoginData(isNew: Boolean,userName: String,password: String): String {
            return Gson().toJson(AuthenticationDataClient(isNew = isNew, userName = userName, password = password))
        }
        fun getLoginData(data: String): AuthenticationDataClient {
            return Gson().fromJson(data, AuthenticationDataClient::class.java)
        }

        fun getFullData(data: String): FullDataClient {
            return Gson().fromJson(data, FullDataClient::class.java)
        }
    }
}