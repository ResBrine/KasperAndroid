package com.fedorkasper.kasper_chat_lite.tool

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fedorkasper.kasper_chat_lite.databinding.ItemChatBinding


class ItemChatDiffCallback : DiffUtil.ItemCallback<ItemChat>(){
    override fun areItemsTheSame(oldItem: ItemChat, newItem: ItemChat): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemChat, newItem: ItemChat): Boolean {
        return  oldItem == newItem
    }

}
class ItemChatViewHolder(private val binding: ItemChatBinding)
    :RecyclerView.ViewHolder(binding.root) {
    fun bind(itemChat: ItemChat, listener: ChatsAdapter.Listener) {
        binding.apply {
            textName.text = itemChat.name
            textLastMessage.text = itemChat.lastMessage
            root.setOnClickListener {
                listener.onClick(itemChat)
            }
        }
    }
}

class ChatsAdapter(
    private val listener: Listener,
):ListAdapter<ItemChat, ItemChatViewHolder>(ItemChatDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemChatViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemChatViewHolder, position:Int){
        holder.bind(getItem(position), listener)
    }

    interface Listener{
        fun onClick(itemChat: ItemChat)
    }
}
