package com.bolhy91.rickandmortyapp.di

import android.app.Application
import androidx.room.Room
import com.bolhy91.rickandmortyapp.data.local.RickDatabase
import com.bolhy91.rickandmortyapp.data.remote.RickApi
import com.bolhy91.rickandmortyapp.utils.Api.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideRickApi(retrofit: Retrofit): RickApi {
        return retrofit.create(RickApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRickDatabase(app: Application): RickDatabase {
        return Room.databaseBuilder(
            app,
            RickDatabase::class.java,
            "rickandmorty.db"
        ).build()
    }
}