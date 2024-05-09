package com.fedorkasper.testpoject.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

interface MessageRepository {
    fun getAll(): LiveData<List<Message>>
    fun reading(id:Int)
    fun addMessage(author:String, text: String,dateTime:Date)
}
class MessageRepositoryInMemoryImpl: MessageRepository {
    private var nextId = 0
    private var messages:List<Message> = listOf(
        Message(
            id = 0,
            author = "Kasper",
            text = "Hello",
            date = Calendar.getInstance().time,
            hasRead = true
        )
    )
    private val data = MutableLiveData(messages)
    override fun getAll(): LiveData<List<Message>> = data
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
    fun reading(id:Int) = repository.reading(id)
    fun addMessage(author: String,text: String,dateTime: Date) = repository.addMessage(author,text,dateTime)
}