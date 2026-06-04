package com.arman.dev.fellafeedsassignment.core.network

import com.arman.dev.fellafeedsassignment.core.utils.Utility
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader(
                "x-api-key",
                Utility.API_KEY
            )
            .build()

        return chain.proceed(newRequest)
    }
}