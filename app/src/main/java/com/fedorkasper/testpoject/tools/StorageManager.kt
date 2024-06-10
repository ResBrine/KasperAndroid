package com.fedorkasper.testpoject.tools

import android.content.Context
import com.fedorkasper.testpoject.itemchat.Message
import com.google.gson.Gson

class StorageManager(context: Context) {
    private val preferences = context.getSharedPreferences("Chats", Context.MODE_PRIVATE)
    private val editor = preferences.edit()
    private val countChat:Int
        get() = preferences.getInt("countChat", -1)

    fun saveMessages(idRoom:Long, messages: List<Message>){
        var lenght = 0
        messages.forEach {
            val json = Gson().toJson(it)
            editor.putString("${idRoom}Message${lenght++}", json)
        }
        editor.putInt("countMessage${idRoom}", lenght)
        editor.apply()
    }
    fun loadMessages(idRoom:Long):List<Message>{
        val length = preferences.getInt("countMessage${idRoom}", -1)
        var messages = listOf<Message>()
        try {

            for (n in 0..<length) {
                val json = preferences.getString("${idRoom}Message${n}", null)
                messages = messages + listOf(
                    Gson().fromJson(
                        json,
                        Message::class.java
                    ) as Message
                )
            }
        }catch (e:Exception)
        {
            e.printStackTrace()
          //  deleteAll()
        }

        return messages
    }
    fun deleteAll()
    {
        editor.clear()
        editor.commit()
    }
}