package com.harismexis.koinpractice.framework.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.koinpractice.BuildConfig
import com.harismexis.koinpractice.datamodel.repository.HeroLocal
import com.harismexis.koinpractice.datamodel.repository.HeroRemote
import com.harismexis.koinpractice.framework.datasource.database.RickAndMortyDatabase
import com.harismexis.koinpractice.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.koinpractice.framework.datasource.database.repository.HeroLocalRepository
import com.harismexis.koinpractice.framework.datasource.network.api.RickAndMortyApi
import com.harismexis.koinpractice.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.koinpractice.framework.datasource.network.repository.HeroRemoteRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideRickAndMortyApi(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideOkHttpClient() }
    single { provideGSON() }
    single { provideLocalDao(androidContext()) }
    single { provideLocalRepository(get()) }
    single { provideRemoteDao(get()) }
    single { provideRemoteRepository(get()) }
}

private fun provideRickAndMortyApi(retrofit: Retrofit): RickAndMortyApi {
    return retrofit.create(RickAndMortyApi::class.java)
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gson: Gson
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.RICK_AND_MORTY_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

private fun provideGSON(): Gson {
    return GsonBuilder().setLenient().create()
}

fun provideLocalDao(app: Context): RickAndMortyLocalDao {
    return RickAndMortyDatabase.getDatabase(app).getDao()
}

fun provideLocalRepository(dao: RickAndMortyLocalDao): HeroLocal {
    return HeroLocalRepository(dao)
}

fun provideRemoteDao(api: RickAndMortyApi): RickAndMortyRemoteDao {
    return RickAndMortyRemoteDao(api)
}

fun provideRemoteRepository(dao: RickAndMortyRemoteDao): HeroRemote {
    return HeroRemoteRepository(dao)
}