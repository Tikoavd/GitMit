package com.practicework.core.retrofit.call_handler

enum class ErrorTypes(val message: String) {
    NO_INTERNET_ACCESS("Can not connect to network"),
    NOT_SUCCESSFULLY_REQUEST("Request is not successfully"),
    COULD_NOT_FETCH("Could not fetch from network"),
    EMPTY_RESPONSE("Response is null")
}