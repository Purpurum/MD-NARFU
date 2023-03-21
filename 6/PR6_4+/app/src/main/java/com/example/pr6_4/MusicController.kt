package com.example.pr6_4

import retrofit2.Call
import retrofit2.http.*

interface MusicController {
    @Headers("x-apikey: " + "53681ed9fe2a2fd4a5654d05bc2911a66ea42")
    @GET("https://music-a177.restdb.io/rest/music")
    fun read(): Call<List<MusicEntry>>
    @Headers("x-apikey: " + "53681ed9fe2a2fd4a5654d05bc2911a66ea42")
    @GET("https://music-a177.restdb.io/rest/music/{id}")
    fun readEntry(@Path("id") id: String): Call<MusicEntry>
    @Headers("x-apikey: " + "53681ed9fe2a2fd4a5654d05bc2911a66ea42")
    @PUT("https://music-a177.restdb.io/rest/music")
    fun create(@Body entry:MusicEntry): Call<Int>
    @Headers("x-apikey: " + "53681ed9fe2a2fd4a5654d05bc2911a66ea42")
    @POST("https://music-a177.restdb.io/rest/music")
    fun update(@Body entry:MusicEntry): Call<Boolean>
    @Headers("x-apikey: " + "53681ed9fe2a2fd4a5654d05bc2911a66ea42")
    @DELETE(value = "https://music-a177.restdb.io/rest/music/{id}")
    fun delete(@Path("id") id: String): Call<Boolean>
}