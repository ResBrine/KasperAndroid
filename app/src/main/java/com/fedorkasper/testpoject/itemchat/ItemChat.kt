package com.fedorkasper.testpoject.itemchat

import java.util.Date

data class ItemChat(
    var id:Int,
    var author:String,
    var text:String,
    var date: Date,
    var hasRead:Boolean
)
