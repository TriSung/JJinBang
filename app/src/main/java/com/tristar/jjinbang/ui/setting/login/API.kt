package com.tristar.jjinbang.ui.setting.login

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class API {
    interface APIService {
        @GET("/names/{name}")
        fun getHello(@Path("name") name: String): Call<ResponseBody>

        @Headers("Content-type: application/json")
        @POST("/api/postName")
        fun postName(@Body body: JsonObject): Call<ResponseBody>
    }

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("http://218.155.251.115:5000/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        var service = retrofit.create(APIService::class.java)
    }
}