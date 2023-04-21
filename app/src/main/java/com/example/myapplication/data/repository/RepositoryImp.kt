package com.example.myapplication.data.repository
import com.example.myapplication.data.datasource.RemoteDataSource
import com.example.myapplication.data.util.Response
import com.example.myapplication.domain.model.RequestModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun getPixelImages(requestBody: RequestModel): Flow<Response> {
        return flow {
            emit(remoteDataSource.getPixelImages(requestBody))
        }
    }
}