package com.fedorkasper.testpoject.itemchat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testpoject.R
import com.example.testpoject.databinding.ItemChatBinding
import com.fedorkasper.testpoject.constants.getHourMinute
import com.fedorkasper.testpoject.fragments.ChatFragment
import com.google.gson.Gson

class ItemChatDiffCallback : DiffUtil.ItemCallback<ItemChat>(){
    override fun areItemsTheSame(oldItem: ItemChat, newItem: ItemChat): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemChat, newItem: ItemChat): Boolean {
        return  oldItem == newItem
    }

}
class ItemChatViewHolder(private val binding: ItemChatBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemChat: ItemChat, listener: ItemChatAdapter.Listener) {
        binding.apply {
            textName.text = itemChat.author
            textLastMessage.text = if (itemChat.messages.isNotEmpty()) itemChat.messages.last().text else "Пусто"
            textDate.text = if (itemChat.messages.isNotEmpty()) getHourMinute(itemChat.messages.last().date) else ""
            root.setOnClickListener {
                it.findNavController().navigate(R.id.action_listChatsFragment_to_chatFragment)
            }
        }
    }
}

class ItemChatAdapter(
    private val listener: Listener
): ListAdapter<ItemChat, ItemChatViewHolder>(ItemChatDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemChatViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemChatViewHolder, position:Int){
        val itemChat = getItem(position)
        holder.bind(itemChat, listener)
    }
    interface Listener{
        fun reading()
    }
}