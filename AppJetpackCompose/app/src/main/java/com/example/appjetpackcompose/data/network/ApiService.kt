package com.example.appjetpackcompose.data.network

import com.example.appjetpackcompose.data.network.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        // Singleton instance
        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
}