package com.example.paysky_crud_assement.domain.usecases

import android.app.Application
import com.example.paysky_crud_assement.data.local.PostDao
import com.example.paysky_crud_assement.data.local.entities.PostsEntity
import com.example.paysky_crud_assement.data.local.entities.toDomain
import com.example.paysky_crud_assement.data.local.entities.toEntity
import com.example.paysky_crud_assement.data.remote.ResponseWrapper
import com.example.paysky_crud_assement.data.remote.apiCallHandler
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import com.example.paysky_crud_assement.utils.hasInternetConnection
import javax.inject.Inject

class ReadUseCase @Inject constructor(
    private val repository: CRUDRepository,
    private val postDao: PostDao,
    private val application: Application
) {

    suspend fun getPost(id: Int): ResponseWrapper<PostsEntity> {
        return apiCallHandler {
            postDao.getPost(id)
        }
    }

    suspend fun getPosts(): ResponseWrapper<List<Post>> {
        return apiCallHandler {
            if (hasInternetConnection(application)) {
                val localPosts = postDao.getAllPosts().mapNotNull { it.toDomain() }
                if (localPosts.isEmpty()) {
                    val remotePosts = repository.getPosts().mapNotNull { it.toEntity() }
                    postDao.insert(remotePosts)
                }
            }
            postDao.getAllPosts().mapNotNull { it.toDomain() }
        }
    }
}
