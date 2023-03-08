package com.example.pr6_4

import retrofit2.Call
import retrofit2.http.*

interface MusicController {
    @GET("/music")
    fun read(): Call<List<MusicEntry>>
    @GET("/music/{id}")
    fun readEntry(@Path("id") id:Int): Call<MusicEntry>
    @PUT("/music")
    fun create(@Body entry:MusicEntry): Call<Int>
    @POST("/music")
    fun update(@Body entry:MusicEntry): Call<Boolean>
    @DELETE(value = "/music/{id}")
    fun delete(@Path("id") id:Int): Call<Boolean>
}