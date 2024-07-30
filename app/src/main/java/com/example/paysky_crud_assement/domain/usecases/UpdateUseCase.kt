package com.example.paysky_crud_assement.domain.usecases

import android.app.Application
import com.example.paysky_crud_assement.data.local.PostDao
import com.example.paysky_crud_assement.data.remote.ResponseWrapper
import com.example.paysky_crud_assement.data.remote.apiCallHandler
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import com.example.paysky_crud_assement.utils.hasInternetConnection
import javax.inject.Inject

class UpdateUseCase @Inject constructor(
    private val repository: CRUDRepository,
    private val postDao: PostDao,
    private val application: Application

) {

    suspend fun updatePost(id: Int, post: Post): ResponseWrapper<Post> {
        return apiCallHandler {
            if (hasInternetConnection(application)) {
                postDao.updatePostById(id, post.title, post.body)
                repository.updatePost(id, post)
            } else {
                postDao.updatePostById(id, post.title, post.body)
                post
            }
        }
    }
}
