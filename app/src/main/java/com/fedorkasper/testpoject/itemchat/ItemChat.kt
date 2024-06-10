package com.fedorkasper.testpoject.itemchat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemChat(
    var id:Long,
    var author:String,
    var hasRead:Boolean,
    var messages:List<Message>
) : Parcelable