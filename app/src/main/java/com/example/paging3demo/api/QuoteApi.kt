package com.example.paging3demo.api

import com.example.paging3demo.modelClass.Movies


import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("/")
    suspend fun getQuotes(
        @Query("apikey") apikey: String,
        @Query("s") s: String,
        @Query("type") type: String,
        @Query("page") page: Int,
    ): Movies/*Response<Movies>*/
}
