package com.fedorkasper.testpoject.itemchat

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fedorkasper.testpoject.tools.StorageManager
import java.util.Calendar

interface ItemChatRepository {
    fun getAll(): LiveData<List<ItemChat>>
    fun setAll(chats:List<ItemChat>)
    fun reading(id:Int)
    fun addItemChat(author:String)
    fun addMessage(itemChat: ItemChat, text: String)
    fun clear()
}
class ItemChatRepositoryInMemoryImpl(context: Context): ItemChatRepository {
    private var nextId = 1
    private var itemChats:List<ItemChat> = listOf()
    private val data = MutableLiveData(itemChats)
    private val storageManager = StorageManager(context)
    private fun sync() {
        data.value = itemChats
        storageManager.saveChats(itemChats)
    }
    override fun getAll(): LiveData<List<ItemChat>> = data
    override fun setAll(chats: List<ItemChat>) {
        if (chats.isNotEmpty())
            itemChats = chats
        sync()
    }

    override fun reading(id: Int) {
        TODO("Not yet implemented")
    }

    override fun addItemChat(author:String) {
        itemChats = listOf(
            ItemChat(
                id = nextId++,
                author = author,
                hasRead = false,
                listOf()
            )
        ) + itemChats
        sync()
    }
    override fun addMessage(itemChat: ItemChat, text: String) {
        itemChats.forEach{
            if (it.id == itemChat.id)
                it.messages += listOf(
                    Message(
                        it.messages.size+1,
                        itemChat.author,
                        text,
                        Calendar.getInstance().time,
                        false
                    )
                )
        }
        sync()
    }

    override fun clear() {
        storageManager.deleteAll()
        itemChats = emptyList()
        sync()
    }

}
class ItemChatViewModal(application: Application): AndroidViewModel(application) {

    private val repository: ItemChatRepository = ItemChatRepositoryInMemoryImpl(application)
    val data = repository.getAll()
    fun setAll(chats:List<ItemChat>) = repository.setAll(chats)
    fun clearAll() = repository.clear()
    fun addItemChat(author: String) = repository.addItemChat(author)
    fun addMessage(itemChat: ItemChat,text: String) = repository.addMessage(itemChat,text)
}