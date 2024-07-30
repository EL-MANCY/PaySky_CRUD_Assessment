package com.example.paysky_crud_assement.data.dto

import com.google.gson.annotations.SerializedName

data class BaseErrorDTO(

    @field:SerializedName("code")
    val code: Int?,

    @field:SerializedName("code")
    val message: String?,

    @field:SerializedName("code")
    val details: String? = null
)
