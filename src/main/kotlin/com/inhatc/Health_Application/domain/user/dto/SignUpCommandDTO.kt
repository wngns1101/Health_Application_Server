package com.inhatc.Health_Application.domain.user.dto

data class SignUpCommandDTO(
    val email: String,
    val password: String,
    val nickname: String,
    val goalCount: Int,
)
