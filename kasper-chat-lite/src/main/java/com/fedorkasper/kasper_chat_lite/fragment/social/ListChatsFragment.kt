package com.fedorkasper.kasper_chat_lite.fragment.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fedorkasper.kasper_chat_lite.databinding.FragmentListChatsBinding
import com.fedorkasper.kasper_chat_lite.tool.ChatsAdapter
import com.fedorkasper.kasper_chat_lite.tool.ItemChat
import com.fedorkasper.kasper_chat_lite.tool.ListChatsModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_LIST_ITEM_CHATS = "param1"
private const val ARG_MINE_ID = "mineId"

/**
 * A simple [Fragment] subclass.
 * Use the [ListChatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListChatsFragment : Fragment() {
    private lateinit var binding:FragmentListChatsBinding
    private val listChatsModel: ListChatsModel by activityViewModels()
    private var listItemChats: String? = null
    private var mineId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            listItemChats = it.getString(ARG_LIST_ITEM_CHATS)
            mineId = it.getInt(ARG_MINE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentListChatsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ChatsAdapter(object: ChatsAdapter.Listener {
            override fun onClick(itemChat: ItemChat) {
                listChatsModel.clearList()
            }
        })
        binding.listChats.adapter = adapter
        listChatsModel.data.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.buttonSetting.setOnClickListener {
            listChatsModel.addItemChat(ItemChat(0,"Ваня","Привет"))
        }
    }

    companion object {
        @JvmStatic fun setMineId(id:Int):Bundle
        {
            return  bundleOf(ARG_MINE_ID to id)
        }
    }
}