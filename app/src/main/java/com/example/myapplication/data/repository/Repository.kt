package com.example.myapplication.data.repository

import com.example.myapplication.data.util.Response
import com.example.myapplication.domain.model.RequestModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPixelImages(requestBody: RequestModel): Flow<Response>

}