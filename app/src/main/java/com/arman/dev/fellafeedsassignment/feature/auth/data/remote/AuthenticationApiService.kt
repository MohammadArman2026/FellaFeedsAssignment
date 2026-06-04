package com.arman.dev.fellafeedsassignment.feature.auth.data.remote

import com.arman.dev.fellafeedsassignment.core.network.ApiResponse
import com.arman.dev.fellafeedsassignment.feature.auth.domain.model.OtpRequest
import com.arman.dev.fellafeedsassignment.feature.auth.domain.model.OtpToken
import com.arman.dev.fellafeedsassignment.feature.auth.domain.model.VerifyOtpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST("apis/users/auth/request-otp/")
    suspend fun requestOtp(
        @Body
        otpRequest: OtpRequest
    ): Response<ApiResponse<Unit>>


    @POST("apis/users/auth/verify-otp/")
    suspend fun verifyOtp(
        @Body
        verifyOtp: VerifyOtpRequest
    ):Response<ApiResponse<OtpToken>>


    @POST("apis/users/auth/logout/")
    suspend fun logout(
        @Header("Authorization")
        authorization : String
    ): Response<ApiResponse<Unit>>

}