package com.test.people.model

import com.google.gson.GsonBuilder

data class ErrorResponse (
    var error: ErrorResponseDescription? = null) {

    fun from (strErrorResponse: String): ErrorResponseDescription? {
        error = GsonBuilder().create().fromJson(strErrorResponse, ErrorResponse::class.java).error
        return error
    }
}

data class ErrorResponseDescription (
    val code: String?,
    val message: String?,
    )