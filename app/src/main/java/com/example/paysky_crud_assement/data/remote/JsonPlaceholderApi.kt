package com.example.paysky_crud_assement.data.remote

import com.example.paysky_crud_assement.domain.models.Post
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JsonPlaceholderApi {

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)

    @GET("posts")
    suspend fun getPosts(): List<Post>
}