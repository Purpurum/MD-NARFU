package ru.myitschool.lab23
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MyViewModel : ViewModel(){
    var result : String = "Тут будет результат"

    fun buildstring(str1: String, str2: String){
        result = (str1.toDouble()/(str2.toDouble()+1)).toString()
    }



}