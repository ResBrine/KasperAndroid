package com.fedorkasper.kasper_chat_lite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fedorkasper.kasper_chat_lite.network.APIManager
data class LoginData(
    var ip:String,
    var port:Int
)
class repos{

}
class DataModel:ViewModel(){
    val loginData:MutableLiveData<LoginData> by lazy {
        MutableLiveData<LoginData>()
    }
    val apiHelper:MutableLiveData<APIManager> = MutableLiveData(APIManager())
}