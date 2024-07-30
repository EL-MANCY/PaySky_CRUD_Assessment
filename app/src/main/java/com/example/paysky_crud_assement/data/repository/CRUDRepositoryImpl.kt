package com.example.paysky_crud_assement.data.repository

import com.example.paysky_crud_assement.data.remote.JsonPlaceholderApi
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import javax.inject.Inject

class CRUDRepositoryImpl @Inject constructor(
    private val apis: JsonPlaceholderApi,
) : CRUDRepository {
    override suspend fun createPost(post: Post): Post {
        return apis.createPost(post)
    }

    override suspend fun getPost(id: Int): Post {
        return apis.getPost(id)
    }

    override suspend fun updatePost(id: Int, post: Post): Post {
        return apis.updatePost(id, post)
    }

    override suspend fun deletePost(id: Int) {
        return apis.deletePost(id)
    }

    override suspend fun getPosts(): List<Post> {
        return apis.getPosts()
    }
}