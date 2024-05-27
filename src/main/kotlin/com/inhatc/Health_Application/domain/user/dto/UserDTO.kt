package com.inhatc.Health_Application.domain.user.dto

data class UserDTO(
    val userId: Long,
    val email: String,
    val nickname: String,
    val goalCount: Int,
)
