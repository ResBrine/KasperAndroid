package com.fedorkasper.testpoject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testpoject.databinding.FragmentChatBinding
import com.fedorkasper.testpoject.itemchat.ItemChat
import com.fedorkasper.testpoject.itemchat.ItemChatViewModal
import com.fedorkasper.testpoject.itemchat.MessageAdapter

private const val ARG_ITEM_CHAT = "itemChat"

class ChatFragment : Fragment() {
    private lateinit var binding:FragmentChatBinding
    private val itemChatViewModal: ItemChatViewModal by activityViewModels()
    @Suppress("DEPRECATION")
    private val itemChat :ItemChat
        get() = requireArguments().get(ARG_ITEM_CHAT) as ItemChat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("DDD","onCreateView")

        binding = FragmentChatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DDD","onViewCreated")

        val adapter = MessageAdapter()
        binding.recyclerViewMessage.adapter = adapter
        binding.textviewTitle.text= itemChatViewModal.data.value?.first { it.id == itemChat.id }?.author.toString()

        itemChatViewModal.data.observe(viewLifecycleOwner){ _ ->
            adapter.submitList(itemChatViewModal.data.value?.first { it.id == itemChat.id }?.messages)
        }

        binding.buttonSend.setOnClickListener {
            var message:String

            binding.edittextMessage.text.apply {
                message = toString()
                clear()
            }
            itemChatViewModal.addMessage(
                itemChat = itemChat.copy().let{
                    if (message.first() == '@'){
                        it.author = "Я"
                        message = message.drop(1)
                    }
                    it
                },
                text = message
            )
        }
    }
    companion object {
        fun setIdChat(id: ItemChat) :Bundle{
            return  bundleOf(ARG_ITEM_CHAT to id)
        }
    }
}