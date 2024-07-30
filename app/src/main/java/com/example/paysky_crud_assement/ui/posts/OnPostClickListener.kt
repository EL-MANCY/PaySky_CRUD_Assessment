package com.example.paysky_crud_assement.ui.posts

import com.example.paysky_crud_assement.domain.models.Post

interface OnPostClickListener {
    fun onEditPostClick(post: Post)
    fun onDeletePostClick(post: Post)
}
