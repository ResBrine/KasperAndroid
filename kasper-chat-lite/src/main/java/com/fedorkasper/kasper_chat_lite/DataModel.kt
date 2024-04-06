package com.fedorkasper.kasper_chat_lite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel:ViewModel(){
    val loginData:MutableLiveData<Array<String>> by lazy {
        MutableLiveData<Array<String>>()
    }

}