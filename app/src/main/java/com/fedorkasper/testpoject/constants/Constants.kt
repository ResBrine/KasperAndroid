package com.fedorkasper.testpoject.constants

import java.util.Date

var userName:String = "Я"

fun getHourMinute(date: Date):String{
    return date.toString().split(':')[0].split(' ').last() + ":" + date.toString().split(':')[1].split(':')[0]
}