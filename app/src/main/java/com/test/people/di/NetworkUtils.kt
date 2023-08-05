package com.test.people.di


import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import com.test.people.api.ApiService
import com.test.people.api.RetrofitClient
import com.test.people.di.App.Companion.EMPTY_STRING
import com.test.people.ui.BuildConfig
import com.test.people.ui.R
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.EMPTY_BYTE_ARRAY
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class NetworkUtils @Inject constructor(val context: Context) {

    val retrofit: ApiService

    val PARAM_ACCESS_KEY = context.getString(R.string.param_access_key)

    val accessKey: String
        get() {
            return context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
                .metaData?.getString(PARAM_ACCESS_KEY) ?: EMPTY_STRING
        }


    init {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                          else HttpLoggingInterceptor.Level.NONE)
            })
            .addInterceptor(Interceptor { chain ->
                var request: Request = chain.request()
                val url: HttpUrl = request.url.newBuilder()
                    .addQueryParameter(PARAM_ACCESS_KEY, accessKey)
                    .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            })
            .build()

        retrofit = Retrofit.Builder()
                           .baseUrl(RetrofitClient.BASE_URL)
                           .client(client)
                           .addConverterFactory(GsonConverterFactory.create())
                           .build()
                           .create(ApiService::class.java)
    }

}