package com.devyouup.novisign.modules.core.utils
import com.devyouup.novisign.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private lateinit var API_BASE_URL : String
    private val httpClient = OkHttpClient.Builder()
    private lateinit var builder : Retrofit.Builder

    fun <S> createService(
        serviceClass: Class<S>,
        baseUrl: String,
        headers: Map<String, String>?
    ): S {
        API_BASE_URL = baseUrl
        addRequestHeaders(headers)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }

    private fun addRequestHeaders(headers: Map<String, String>?) {
        if (null != headers){

            httpClient.interceptors().add(Interceptor { chain ->
                val original = chain.request()
                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                for((key, value) in headers){
                    requestBuilder.header(key, value)
                    requestBuilder.method(original.method, original.body)
                }
                val request = requestBuilder.build()
                if (BuildConfig.DEBUG){debugRequests(chain, request)}else{chain.proceed(request)}
            })
        }else {
            httpClient.interceptors().add(Interceptor { chain ->
                val original = chain.request()
                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .method(original.method, original.body)

                val request = requestBuilder.build()
                if (BuildConfig.DEBUG){debugRequests(chain, request)}else{chain.proceed(request)}
            })
        }
    }

    private fun debugRequests(
        chain: Interceptor.Chain,
        request: Request
    ): Response {
        val res = chain.proceed(request)
        val responseBody: ResponseBody? = res.body
        val s: String = res.body?.string() ?: ""
        return res.newBuilder().body(s.toResponseBody(responseBody?.contentType())).build()
    }
}