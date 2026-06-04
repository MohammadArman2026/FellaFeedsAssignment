package com.arman.dev.fellafeedsassignment.core.network

import com.arman.dev.fellafeedsassignment.core.utils.Utility
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${Utility.AUTH_TOKEN}"
            )
            .build()

        return chain.proceed(request)
    }
}