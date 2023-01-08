package id.buaja.covid19.di

import id.buaja.covid19.BuildConfig
import id.buaja.covid19.data.source.service.CovidApiService
import id.buaja.covid19.data.source.service.NewsApiService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single(named("BASE")) {
        createRetrofit(
            baseUrl = BuildConfig.BASE_URL
        )
    }

    single(named("NEWS")) {
        createRetrofit(
            baseUrl = "https://newsapi.org/v2/"
        )
    }

    single {
        createNetworkApi(get(named("NEWS")))
    }

    single {
        createCovidApiService(get(named("BASE")))
    }
}

fun createRetrofit(
    baseUrl: String
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(createOkHttpClient())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .pingInterval(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun createNetworkApi(retrofit: Retrofit) : NewsApiService {
    return retrofit.create(NewsApiService::class.java)
}

fun createCovidApiService(retrofit: Retrofit): CovidApiService {
    return retrofit.create(CovidApiService::class.java)
}