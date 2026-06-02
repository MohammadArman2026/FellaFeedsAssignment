package com.arman.dev.fellafeedsassignment.feature.login.data.remote

import com.arman.dev.fellafeedsassignment.core.network.ApiResponse
import com.arman.dev.fellafeedsassignment.feature.login.data.model.OtpRequest
import com.arman.dev.fellafeedsassignment.feature.login.data.model.OtpToken
import com.arman.dev.fellafeedsassignment.feature.login.data.model.VerifyOtpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService{
    /**
     * requesting otp from the backend.
     */
    @POST("apis/users/auth/request-otp/")
    suspend fun requestOtpRequest(
        @Header("X-API-KEY")
        apiKey: String,
        @Body
        otpRequest: OtpRequest
    ): Response<ApiResponse<Unit>>

    /**
     * verifying the otp sent by the backend.
     */
    @POST("apis/users/auth/verify-otp/")
    suspend fun verifyOtp(
        @Header("X-API-KEY")
        apiKey: String ,
        @Body
        verifyOtp: VerifyOtpRequest
    ): Response<ApiResponse<OtpToken>>

    /**
     * requesting logout.
     */
    @POST("apis/users/auth/logout/")
    suspend fun postLogout(
        @Header("Authorization")
         token: String ,
        @Header("X-API-KEY")
        apiKey: String
    ): Response<ApiResponse<Unit>>
}