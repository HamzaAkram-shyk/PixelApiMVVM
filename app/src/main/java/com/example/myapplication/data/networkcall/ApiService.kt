package com.example.myapplication.data.networkcall

import com.example.myapplication.domain.model.PixelResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/")
    suspend fun getPixelResponse(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String,
        @Query("pretty") pretty: Boolean = true,
    ): Response<ResponseBody>

}
