package com.example.myapplication.data.di

import com.example.myapplication.data.datasource.RemoteDataSource
import com.example.myapplication.data.datasource.RemoteDataSourceImp
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.data.repository.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class InterfacesModules {
    @Binds
    @Singleton
    abstract fun bindDataSource(dataSource: RemoteDataSourceImp): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(repository: RepositoryImp): Repository
}