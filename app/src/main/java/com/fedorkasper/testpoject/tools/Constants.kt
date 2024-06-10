package com.fedorkasper.testpoject.tools

import java.util.Date

var userName:String = "Я"
var iUser: APIManager.FullDataClient? = null
var myid:Long = 0
fun getHourMinute(date: Date):String{
    return date.toString().split(':')[0].split(' ').last() + ":" + date.toString().split(':')[1].split(':')[0]
}