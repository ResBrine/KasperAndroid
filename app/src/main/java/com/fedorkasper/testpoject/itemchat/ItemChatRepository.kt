package com.fedorkasper.testpoject.itemchat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fedorkasper.testpoject.message.Message
import java.util.Calendar
import java.util.Date

interface ItemChatRepository {
    fun getAll(): LiveData<List<ItemChat>>
    fun reading(id:Int)
    fun addItemChat(author:String)
    fun changeLastMessage(id: Int, message: Message)
}
class ItemChatRepositoryInMemoryImpl: ItemChatRepository {
    private var nextId = 0
    private var itemChats:List<ItemChat> = listOf()
    private val data = MutableLiveData(itemChats)
    override fun getAll(): LiveData<List<ItemChat>> = data
    override fun reading(id: Int) {
        TODO("Not yet implemented")
    }

    override fun addItemChat(author:String) {
        itemChats = listOf(
            ItemChat(
                id = nextId++,
                author = author,
                text = "Пока нету сообщений",
                date = Calendar.getInstance().time,
                hasRead = false
            )
        ) + itemChats
        data.value = itemChats
    }

    override fun changeLastMessage(id: Int, message: Message) {
        itemChats.map {
            if (it.id==id)
                it.apply {
                    text = message.text
                    author = message.author
                }
            it
        }
        data.value = itemChats
    }
}
class ItemChatViewModal: ViewModel() {

    private val repository: ItemChatRepository = ItemChatRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun reading(id:Int) = repository.reading(id)
    fun addItemChat(author: String) = repository.addItemChat(author)
    fun changeLastMessage(id: Int,message:Message) = repository.changeLastMessage(id,message)
}