package com.inhatc.Health_Application.api.user.http.req

import com.inhatc.Health_Application.domain.user.dto.SignInCommand

data class SignInUserRequestDTO (
    val email: String,
    val password: String,
)

fun SignInUserRequestDTO.toCommand(): SignInCommand {
    return SignInCommand(
        email = email,
        password = password,
    )
}