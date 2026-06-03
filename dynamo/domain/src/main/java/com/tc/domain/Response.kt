package com.tc.domain

sealed class Response {
    data object Loading : Response()
    data class Success<out T>(val data : T) : Response()
    data class Failure(val msg : String) : Response()
}