package com.arman.dev.fellafeedsassignment.feature.auth.domain.repository

import com.arman.dev.fellafeedsassignment.core.common.Resource

interface AuthenticationRepository {
    suspend fun requestOtp(phoneNumber : String): Resource<String>
    suspend fun verifyOtp(phoneNumber : String , otp: String): Resource<String>
}