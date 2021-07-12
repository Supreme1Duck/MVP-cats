package com.example.mvpcats.model.api

import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.util.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatsApi {

    @GET("images/search")
    fun getCats(
           @Header("x-api-key") api_key : String,
           @Query("format") format : String,
           @Query("order") order : String,
           @Query("size") size : String,
           @Query("limit") limit : Int,
           @Query("page") page : Int,
    ) : Single<CatsModel>


    companion object Factory{
        fun create() : CatsApi{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.CATS_URL)
                .build()

            return retrofit.create(CatsApi::class.java)
        }
    }
}