package com.fedorkasper.testpoject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testpoject.databinding.MessageBinding

class MessageDiffCallback : DiffUtil.ItemCallback<Message>(){
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return  oldItem == newItem
    }

}
class MessageViewHolder(private val binding: MessageBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message,listener: MessageAdapter.Listener) {
        binding.apply {
            if (message.author == userName){
                textViewMessageMy.text = message.text
                textViewDataTimeMy.text = getHourMinute(message.date)
                constraintLayoutMy.visibility = View.VISIBLE
                constraintLayoutMe.visibility = View.GONE
            }else{
                textViewMessageMe.text = message.text
                textViewDataTimeMe.text = getHourMinute(message.date)
                constraintLayoutMe.visibility = View.VISIBLE
                constraintLayoutMy.visibility = View.GONE
                listener.reading()
            }

        }
    }
}

class MessageAdapter(
    private val listener: Listener
): ListAdapter<Message, MessageViewHolder>(MessageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MessageViewHolder, position:Int){
        val message = getItem(position)
        holder.bind(message, listener)
    }
    interface Listener{
        fun reading()
    }
}