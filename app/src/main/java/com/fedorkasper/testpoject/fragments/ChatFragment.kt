package com.fedorkasper.testpoject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.testpoject.databinding.FragmentChatBinding
import com.fedorkasper.testpoject.itemchat.ItemChat
import com.fedorkasper.testpoject.itemchat.ItemChatViewModal
import com.fedorkasper.testpoject.message.MessageAdapter
import com.fedorkasper.testpoject.message.MessageViewModal
import com.google.gson.Gson
import java.util.Calendar

class ChatFragment : Fragment() {
    private lateinit var binding:FragmentChatBinding
    private val messageViewModal: MessageViewModal by activityViewModels()
    private val itemChatViewModal: ItemChatViewModal by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MessageAdapter(object :MessageAdapter.Listener{
            override fun reading() {
                TODO("Not yet implemented")
            }
        })

        binding.recyclerViewMessage.adapter = adapter

        messageViewModal.data.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.buttonSend.setOnClickListener {
            messageViewModal.addMessage("Я", binding.edittextMessage.text.toString(),Calendar.getInstance().time)
        }
    }

}