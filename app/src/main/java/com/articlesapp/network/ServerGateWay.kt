package com.articlesapp.network

import com.articlesapp.model.MostPopularArticlesResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import javax.net.ssl.X509TrustManager


private const val SERVER_BASE_URL = "https://api.nytimes.com/"
private const val API_KEY = "vGqrF58hrqK7hhUKrtxAdGoYLRgRFxFE"
private const val GET_MOST_POPULAR_ARTICLES = "svc/mostpopular/v2/viewed/1.json?api-key=$API_KEY"

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

val provideOkHttpClientBuilder: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .hostnameVerifier(HostnameVerifier { _: String?, _: SSLSession? -> true })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS).addInterceptor(provideHttpLoggingInterceptor())
        .build()
}
private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(provideOkHttpClientBuilder)
        .build()
}

val articlesApis: ArticlesApis by lazy {
    retrofit.create(ArticlesApis::class.java)
}

interface ArticlesApis {
    @GET(GET_MOST_POPULAR_ARTICLES)
    fun getMostPopularArticles(): Single<MostPopularArticlesResponse>

}