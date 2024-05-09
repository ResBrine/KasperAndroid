package com.fedorkasper.testpoject.itemchat

import com.fedorkasper.testpoject.message.Message
import com.fedorkasper.testpoject.message.MessageViewModal
import java.util.Date

data class ItemChat(
    var id:Int,
    var author:String,
    var hasRead:Boolean,
    var messages:List<Message>,
)
