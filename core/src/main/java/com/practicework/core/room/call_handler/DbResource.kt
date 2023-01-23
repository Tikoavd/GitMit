package com.practicework.core.room.call_handler

sealed class DbResource<out T> {
    class Success<T>(val model: T): DbResource<T>()
    object Error : DbResource<Nothing>()
}
