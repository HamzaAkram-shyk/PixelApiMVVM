package com.example.myapplication.data.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

inline fun <reified T> Moshi.fromJson(json: String): T? {
    val type = object : TypeReference<T>() {}.type
    val jsonAdapter : JsonAdapter<T> = adapter(type)
    return jsonAdapter.fromJson(json)
}


