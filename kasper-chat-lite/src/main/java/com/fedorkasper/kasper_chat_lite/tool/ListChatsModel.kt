package com.fedorkasper.kasper_chat_lite.tool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private interface ItemChatRepository{
    fun getAll():LiveData<List<ItemChat>>
    fun addItemChat(itemChat: ItemChat)
    fun clearList()
}
private class ItemChatRepositoryInMemoryImpl: ItemChatRepository {
    private var itemChats = listOf<ItemChat>()
    private val data = MutableLiveData(itemChats)
    override fun getAll(): LiveData<List<ItemChat>> = data
    override fun addItemChat(itemChat: ItemChat) {
        itemChats += listOf(itemChat)
        data.value = itemChats
    }
    override fun clearList(){
        itemChats = emptyList()
        data.value = itemChats
    }

}
class ListChatsModel: ViewModel(){
    private val repository = ItemChatRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun addItemChat(itemChat: ItemChat) = repository.addItemChat(itemChat)
    fun clearList() = repository.clearList()
}
