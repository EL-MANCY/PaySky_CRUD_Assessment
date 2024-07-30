package com.example.paysky_crud_assement.domain.di

import android.content.Context
import androidx.room.Room
import com.example.paysky_crud_assement.data.local.PostDao
import com.example.paysky_crud_assement.data.local.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
    ): PostsDatabase {
        return Room.databaseBuilder(context, PostsDatabase::class.java, "PostsDatabase")
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: PostsDatabase): PostDao {
        return database.postDao
    }

}