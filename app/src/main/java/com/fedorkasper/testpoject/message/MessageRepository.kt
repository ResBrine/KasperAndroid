package com.fedorkasper.testpoject.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

interface MessageRepository {
    fun getAll(): LiveData<List<Message>>
    fun setListMessage(listMessage:List<Message>)
    fun reading(id:Int)
    fun addMessage(author:String, text: String,dateTime:Date)
}
class MessageRepositoryInMemoryImpl: MessageRepository {
    private var nextId = 0
    private var messages:List<Message> = listOf()
    private val data = MutableLiveData(messages)
    override fun getAll(): LiveData<List<Message>> = data
    override fun setListMessage(listMessage: List<Message>) {
        messages = listMessage
        data.value = messages
    }

    override fun reading(id: Int) {
        TODO("Not yet implemented")
    }

    override fun addMessage(author:String, text: String,dateTime:Date) {
        messages = listOf(
            Message(
                id = nextId++,
                author = author,
                text = text,
                date = dateTime,
                hasRead = false
            )
        ) + messages
        data.value = messages
    }
}
class MessageViewModal: ViewModel() {

    private val repository: MessageRepository = MessageRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun setListMessage(listMessage: List<Message>) = repository.setListMessage(listMessage)
    fun reading(id:Int) = repository.reading(id)
    fun addMessage(author: String,text: String,dateTime: Date) = repository.addMessage(author,text,dateTime)
}