package com.example.myapplication.data.datasource


import com.example.myapplication.BuildConfig
import com.example.myapplication.data.networkcall.ApiService
import com.example.myapplication.data.util.Response
import com.example.myapplication.domain.model.RequestModel
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {

    override suspend fun getPixelImages(requestBody: RequestModel): Response {
        return try {
            val response = apiService.getPixelResponse(
                BuildConfig.Api_Key,
                requestBody.query,
                requestBody.imageType
            )
            if (response.isSuccessful) {
                Response.Success(response.body()?.string()!!)
            } else {
                Response.Failure(response.code(), response.errorBody()?.string()!!)
            }
        } catch (e: Exception) {
            Response.Exception(e)
        }

    }
}