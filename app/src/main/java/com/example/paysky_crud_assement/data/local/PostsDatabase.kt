package com.example.paysky_crud_assement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.paysky_crud_assement.data.local.entities.PostsEntity


@Database(
    entities = [PostsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PostsDatabase : RoomDatabase() {
    abstract val postDao: PostDao
}