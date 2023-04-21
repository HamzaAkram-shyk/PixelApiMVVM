package com.example.myapplication.domain

import com.example.myapplication.data.repository.Repository
import com.example.myapplication.data.util.Response
import com.example.myapplication.domain.model.RequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PixelFetchUC @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(params: RequestModel): Flow<Response> {
        return repository.getPixelImages(params).flowOn(Dispatchers.IO)
    }
}