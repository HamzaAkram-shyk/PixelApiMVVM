package com.example.myapplication.data.datasource

import com.example.myapplication.data.util.Response
import com.example.myapplication.domain.model.RequestModel

interface RemoteDataSource {
   suspend fun getPixelImages(requestBody: RequestModel): Response
}