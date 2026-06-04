package com.tc.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DynamoApi {

    @GET("forms")
    suspend fun getAllForms() : Response<FormResponseDTO>

    @GET("forms/{id}")  // baseURL.com/forms/android if getForm("android")
    suspend fun getForm(@Path("id") id :String) : Response<FormDTO>

}