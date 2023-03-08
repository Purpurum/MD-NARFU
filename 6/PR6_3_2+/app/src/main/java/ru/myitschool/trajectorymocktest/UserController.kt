package ru.myitschool.trajectorymocktest

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*


interface UserController {
    @GET("https://api.github.com/users/{login}")
    fun getUser(@Path("login") login: String): Call<User>
}