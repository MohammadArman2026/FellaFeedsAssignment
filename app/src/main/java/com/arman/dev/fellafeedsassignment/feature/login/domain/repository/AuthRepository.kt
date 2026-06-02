package com.arman.dev.fellafeedsassignment.feature.login.domain.repository

import com.arman.dev.fellafeedsassignment.core.common.Resource

interface AuthRepository{
    suspend fun requestOtp(phone : String): Resource<String>
    suspend fun verifyOtp(otp:String , phone : String): Resource<String>
    suspend fun getInfoAboutMe()
    suspend fun logout(): Resource<String>
}