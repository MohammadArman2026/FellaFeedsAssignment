package com.arman.dev.fellafeedsassignment.feature.auth.data.repository

import com.arman.dev.fellafeedsassignment.core.common.Resource
import com.arman.dev.fellafeedsassignment.feature.auth.data.remote.AuthenticationApiService
import com.arman.dev.fellafeedsassignment.feature.auth.domain.model.OtpRequest
import com.arman.dev.fellafeedsassignment.feature.auth.domain.model.VerifyOtpRequest
import com.arman.dev.fellafeedsassignment.feature.auth.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepoImpl @Inject constructor(
    private val authenticationApiService: AuthenticationApiService
) : AuthenticationRepository {
    override suspend fun requestOtp(phoneNumber: String): Resource<String> {

        return try {
            val otpRequest = OtpRequest(phoneNumber.withCountryCode())
            val response = authenticationApiService.requestOtp(otpRequest)

            if (response.isSuccessful) {
                val body = response.body()

                if (body?.success == true) {
                    Resource.Success(body.message)
                } else {
                    Resource.Error(
                        body?.message
                            ?: "Unknown error"
                    )
                }
            } else {
                Resource.Error(
                    "HTTP ${
                        response.code()
                    }"
                )
            }
        } catch (e: kotlin.Exception) {
            Resource.Error(
                e.message ?: "Unknown error"
            )
        }

    }

    override suspend fun verifyOtp(phoneNumber: String, otp: String): Resource<String> {
        return try {
            val verifyOtpRequest = VerifyOtpRequest(
                otp = otp,
                phoneNumber = phoneNumber.withCountryCode()
            )

            val response = authenticationApiService
                .verifyOtp(
                    verifyOtp = verifyOtpRequest
                )

            if (response.isSuccessful) {

                val body = response.body()

                if (body?.success == true) {
                    Resource.Success(body.data?.token ?: "unknown error")
                } else {
                    Resource.Error(body?.message ?: "unknown error")
                }
            } else {
                Resource.Error(
                    "HTTP Error"
                )
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}

fun String.withCountryCode(): String {
    return if (startsWith("+91")) this else "+91$this"
}

