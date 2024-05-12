package com.fedorkasper.testpoject.itemchat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Message(
    var id:Int,
    var author:String,
    var text:String,
    var date: Date,
    var hasRead:Boolean
) : Parcelable
