package com.example.myapplication.data.util

sealed class Response {
    class Success(val responseBody: String) : Response()
    class Exception(val e: kotlin.Exception) : Response()
    class Failure(val errorCode: Int,val errorBody:Any) : Response()
}
