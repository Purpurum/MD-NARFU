package com.example.PR_3_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.PR_3_1.Model.UserModelClass
import com.example.pr_3_1.R
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    val usersList: ArrayList<UserModelClass> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            // возврщает  объект json
            val obj = JSONObject(getJSONFromAssets()!!)
            //  получение пользователй из json
            val usersArray = obj.getJSONArray("persons")
            // цикл получения данных для пользв
            for (i in 0 until usersArray.length()) {
                // создание обхекта для получения данных для 1го пользв
                val user = usersArray.getJSONObject(i)
                val name = user.getString("name")
                val sex = user.getString("sex")
                val phoneNumber = user.getString("phoneNumber")

                // добавление всех данных в класс
                val userDetails =
                    UserModelClass(sex, name, phoneNumber)

                usersList.add(userDetails)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

            //  настройка адаптера
        val rvUsersList = findViewById<RecyclerView>(R.id.rvUsersList)
        // Set the LayoutManager that this RecyclerView will use.
        rvUsersList.layoutManager = LinearLayoutManager(this)
        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = UserAdapter(this, usersList)
        // adapter instance is set to the recyclerview to inflate the items.
        rvUsersList.adapter = itemAdapter
    }

    fun sortByName(view: View){
        // сортирвока при помощи функции (sortedby)/ ИНИЦИАЛЬЗАЦ КЛАССА АДПТЕРА И ПЕРЕДЧАВ ПАРАМЕТР
        usersList.sortBy { UserModelClass -> UserModelClass.name }
        val rvUsersList = findViewById<RecyclerView>(R.id.rvUsersList)
        rvUsersList.layoutManager = LinearLayoutManager(this)
        val itemAdapter = UserAdapter(this, usersList)
        rvUsersList.adapter = itemAdapter
    }
    fun sortBySex(view: View){
        usersList.sortBy { UserModelClass -> UserModelClass.sex }
        val rvUsersList = findViewById<RecyclerView>(R.id.rvUsersList)
        rvUsersList.layoutManager = LinearLayoutManager(this)
        val itemAdapter = UserAdapter(this, usersList)
        rvUsersList.adapter = itemAdapter
    }fun sortByPhone(view: View){
        usersList.sortBy { UserModelClass -> UserModelClass.phoneNumber }
        val rvUsersList = findViewById<RecyclerView>(R.id.rvUsersList)
        rvUsersList.layoutManager = LinearLayoutManager(this)
        val itemAdapter = UserAdapter(this, usersList)
        rvUsersList.adapter = itemAdapter
    }
 // заугрзка json
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("persons.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}