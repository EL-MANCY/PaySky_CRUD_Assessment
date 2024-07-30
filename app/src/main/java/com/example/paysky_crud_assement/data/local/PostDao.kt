package com.example.paysky_crud_assement.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.paysky_crud_assement.data.local.entities.PostsEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<PostsEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(posts: PostsEntity): Long

    @Query("UPDATE posts SET title = :title, body = :body WHERE id = :id")
    suspend fun updatePostById(id: Int, title: String, body: String)

    @Query("DELETE FROM posts WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM posts WHERE id = :id")
    suspend fun getPost(id: Int): PostsEntity

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostsEntity>
}
