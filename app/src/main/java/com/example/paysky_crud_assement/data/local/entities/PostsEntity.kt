package com.example.paysky_crud_assement.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paysky_crud_assement.domain.models.Post

@Entity(tableName = "posts")
data class PostsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val body: String,

    val userId: Int
)

fun Post.toEntity(): PostsEntity {
    return PostsEntity(id = this.id, title = this.title, body = this.body, userId = this.userId)
}

fun PostsEntity.toDomain(): Post {
    return Post(id = this.id, title = this.title, body = this.body, userId = this.userId)
}
