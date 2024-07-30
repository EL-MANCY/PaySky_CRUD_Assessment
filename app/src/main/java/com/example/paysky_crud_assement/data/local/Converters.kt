package com.example.paysky_crud_assement.data.local

import androidx.room.TypeConverter
import com.example.paysky_crud_assement.data.local.entities.PostsEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromOwnerEntity(post: PostsEntity): String {
        return Gson().toJson(post)
    }

    @TypeConverter
    fun toUserEntity(json: String): PostsEntity {
        return Gson().fromJson(json, PostsEntity::class.java)
    }
}
