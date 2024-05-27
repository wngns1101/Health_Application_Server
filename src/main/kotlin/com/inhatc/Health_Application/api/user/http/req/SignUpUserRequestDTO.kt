package com.inhatc.Health_Application.api.user.http.req

import com.inhatc.Health_Application.domain.user.dto.SignUpCommandDTO

data class SignUpUserRequestDTO(
    val email: String,
    val password: String,
    val nickname: String,
    val goalCount: Int,
)

fun SignUpUserRequestDTO.toCommand(): SignUpCommandDTO {
    return SignUpCommandDTO(
        email = email,
        password = password,
        nickname = nickname,
        goalCount = goalCount,
    )
}
