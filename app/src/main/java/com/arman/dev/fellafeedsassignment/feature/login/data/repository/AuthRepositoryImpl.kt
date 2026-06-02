package com.arman.dev.fellafeedsassignment.feature.login.data.repository

import com.arman.dev.fellafeedsassignment.core.common.Resource
import com.arman.dev.fellafeedsassignment.feature.login.data.model.OtpRequest
import com.arman.dev.fellafeedsassignment.feature.login.data.model.VerifyOtpRequest
import com.arman.dev.fellafeedsassignment.feature.login.data.remote.AuthApiService
import com.arman.dev.fellafeedsassignment.feature.login.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton


class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
): AuthRepository{
    override suspend fun requestOtp(phone: String): Resource<String> {
       return try {
           val otpRequest = OtpRequest(phone)
           val response = authApiService.requestOtpRequest("apikey", otpRequest)

           if(response.isSuccessful){
               val body = response.body()

               if(body?.success == true){
                   Resource.Success(body.message)
               }else{
                   Resource.Error(body?.message
                       ?: "Unknown error")
               }
           }else{
               Resource.Error(
                   "HTTP ${
                       response.code()
                   }"
               )
           }
       }catch (e : kotlin.Exception){
           Resource.Error(
               e.message?:"Unknown error"
           )
       }

    }

    override suspend fun verifyOtp(otp: String, phone: String): Resource<String> {
        return try {
            val verifyOtpRequest = VerifyOtpRequest(
                otp = otp ,
                phoneNumber = phone
            )

            val response = authApiService
                .verifyOtp(
                    apiKey = "my-api-key" ,
                    verifyOtp = verifyOtpRequest
            )

            if(response.isSuccessful){

                val body = response.body()

                if(body?.success == true){
                    Resource.Success(body.data?.token ?:"unknown error" )
                }else{
                    Resource.Error(body?.message ?: "unknown error")
                }
            }else{
                Resource.Error(
                    "HTTP Error"
                )
            }
        }catch (e:Exception){
            Resource.Error(e.message?:"Unknown error")
        }
    }

    override suspend fun getInfoAboutMe() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() : Resource<String>{

        return try {
            val response = authApiService
                .postLogout(
                token = "my token" ,
                apiKey = "my api key"
            )

            if(response.isSuccessful){
                val body = response.body()

                if(body?.success == true){
                    Resource.Success(body.message)
                }else{
                    Resource.Error(body?.message ?:"Unknown error" )
                }
            }else{
                Resource.Error(
                    "Http Error"
                )
            }
        }catch (e : Exception){
            Resource.Error(e.message?:"unknown error")
        }
    }
}