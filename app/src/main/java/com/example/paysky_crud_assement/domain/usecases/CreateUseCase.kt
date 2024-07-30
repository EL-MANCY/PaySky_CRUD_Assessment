package com.example.paysky_crud_assement.domain.usecases

import android.app.Application
import com.example.paysky_crud_assement.data.local.PostDao
import com.example.paysky_crud_assement.data.local.entities.toEntity
import com.example.paysky_crud_assement.data.remote.ResponseWrapper
import com.example.paysky_crud_assement.data.remote.apiCallHandler
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import com.example.paysky_crud_assement.utils.hasInternetConnection
import javax.inject.Inject

class CreateUseCase @Inject constructor(
    private val repository: CRUDRepository,
    private val postDao: PostDao,
    private val application: Application
) {

    suspend fun createPost(post: Post): ResponseWrapper<Post> {
        return apiCallHandler {
            if (hasInternetConnection(application)) {
                postDao.insertPost(post.toEntity())
                repository.createPost(post)
            } else {
                postDao.insertPost(post.toEntity())
                post
            }
        }
    }
}
