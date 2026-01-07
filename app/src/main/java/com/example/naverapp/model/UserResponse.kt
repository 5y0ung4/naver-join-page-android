package com.example.naverapp.model

data class UserResponse (
    val userId: String,
    val password: String,
    val name : String,
    val year : String,
    val month : String,
    val day : String,
    val sex : String,
    val phone : String,
    val email : String
)