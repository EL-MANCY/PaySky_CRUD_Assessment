package com.example.paysky_crud_assement.domain.repository

import com.example.paysky_crud_assement.domain.models.Post

interface CRUDRepository {

    suspend fun createPost(post: Post): Post

    suspend fun getPost(id: Int): Post

    suspend fun updatePost(id: Int, post: Post): Post

    suspend fun deletePost(id: Int)

    suspend fun getPosts(): List<Post>
}