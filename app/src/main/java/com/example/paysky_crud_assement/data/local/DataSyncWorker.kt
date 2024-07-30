package com.example.paysky_crud_assement.data.local

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class DataSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: CRUDRepository,
    private val postDao: PostDao,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // Fetch remote and local posts
            val remotePosts = repository.getPosts()
            val localPosts = postDao.getAllPosts()

            // Determine which posts need to be updated
            val postsToUpdate = remotePosts.filter { remotePost ->
                val localPost = localPosts.find { it.id == remotePost.id }
                localPost == null
            }

            // Update local database with the necessary posts
            postsToUpdate.forEach { repository.updatePost(it.id, it) }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
