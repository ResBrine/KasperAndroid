package com.fedorkasper.testpoject.tools

import android.content.Context
import com.fedorkasper.testpoject.itemchat.ItemChat
import com.google.gson.Gson

class StorageManager(context: Context) {
    private val preferences = context.getSharedPreferences("Chats", Context.MODE_PRIVATE)
    private val editor = preferences.edit()
    private val countChat:Int
        get() = preferences.getInt("countChat", -1)
    fun saveChats(chats: List<ItemChat>){
        var lenght = 0
        chats.forEach {
            val json = Gson().toJson(it)
            editor.putString("Chat${lenght++}", json)
        }
        editor.putInt("countChat", lenght)

        editor.apply()
    }
    fun loadChats():List<ItemChat>{
        val length = countChat
        var chats = listOf<ItemChat>()
        for (n in 0..<length) {
            val json = preferences.getString("Chat${n}", null)
            chats = chats + listOf(
                Gson().fromJson(
                    json,
                    ItemChat::class.java
                ) as ItemChat
            )
        }
        return chats
    }
    fun deleteAll()
    {
        editor.clear()
        editor.commit()
    }
}