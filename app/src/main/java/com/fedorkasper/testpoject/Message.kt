package com.fedorkasper.testpoject

import java.util.Date

data class Message(
    var id:Int,
    var author:String,
    var text:String,
    var date: Date,
    var hasRead:Boolean
)
