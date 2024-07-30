package com.example.paysky_crud_assement.ui.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.paysky_crud_assement.data.remote.ResponseWrapper
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.domain.usecases.CreateUseCase
import com.example.paysky_crud_assement.domain.usecases.DeleteUseCase
import com.example.paysky_crud_assement.domain.usecases.ReadUseCase
import com.example.paysky_crud_assement.domain.usecases.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val readUseCase: ReadUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val updateUseCase: UpdateUseCase,
    private val createUseCase: CreateUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _loading: MutableStateFlow<Boolean?> = MutableStateFlow(false)
    val loading: StateFlow<Boolean?> = _loading

    private val _error: MutableSharedFlow<String?> = MutableSharedFlow()
    val error: SharedFlow<String?> = _error

    val postsResponse: MutableSharedFlow<List<Post>> = MutableSharedFlow()
    val updatePostResponse: MutableSharedFlow<Post> = MutableSharedFlow()
    val insertPostResponse: MutableSharedFlow<Post> = MutableSharedFlow()
    val deleteResponse: MutableSharedFlow<Boolean> = MutableSharedFlow()

    fun getPosts() {
        _loading.value = true
        viewModelScope.launch {
            val result = readUseCase.getPosts()
            _loading.value = false
            when (result) {
                is ResponseWrapper.Success -> postsResponse.emit(result.data)
                is ResponseWrapper.Error -> setError(result.message)
            }
        }
    }

    fun deletePost(id: Int) {
        _loading.value = true
        viewModelScope.launch {
            val result = deleteUseCase.deletePost(id)
            _loading.value = false
            when (result) {
                is ResponseWrapper.Success -> deleteResponse.emit(true)
                is ResponseWrapper.Error -> setError(result.message)
            }
        }
    }

    fun updatePost(id: Int, post: Post) {
        _loading.value = true
        viewModelScope.launch {
            val result = updateUseCase.updatePost(id, post)
            _loading.value = false
            when (result) {
                is ResponseWrapper.Success -> updatePostResponse.emit(result.data)
                is ResponseWrapper.Error -> setError(result.message)
            }
        }
    }

    fun createPost(post: Post) {
        _loading.value = true
        viewModelScope.launch {
            val result = createUseCase.createPost(post)
            _loading.value = false
            when (result) {
                is ResponseWrapper.Success -> insertPostResponse.emit(result.data)
                is ResponseWrapper.Error -> setError(result.message)
            }
        }
    }

    private suspend fun setError(errorMessage: String?) {
        _error.emit(errorMessage)
    }
}
