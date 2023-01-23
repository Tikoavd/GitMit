package com.practicework.core.retrofit.call_handler

sealed class Resource<out T>{
    class Success<T>(val model: T): Resource<T>()
    class Error<T>(val exception: Exception, var model : T?): Resource<T>()
    object Loading: Resource<Nothing>()
}