package com.example.paysky_crud_assement.domain.usecases

import android.app.Application
import com.example.paysky_crud_assement.data.local.PostDao
import com.example.paysky_crud_assement.data.remote.ResponseWrapper
import com.example.paysky_crud_assement.data.remote.apiCallHandler
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import com.example.paysky_crud_assement.utils.hasInternetConnection
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val repository: CRUDRepository,
    private val postDao: PostDao,
    private val application: Application
) {

    suspend fun deletePost(id: Int): ResponseWrapper<Unit> {
        return apiCallHandler {
            if (hasInternetConnection(application)) {
                postDao.deleteById(id)
                repository.deletePost(id)
            } else {
                postDao.deleteById(id)
            }
        }
    }
}
