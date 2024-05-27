package com.inhatc.Health_Application.api.user.application

import com.inhatc.Health_Application.api.user.http.req.SignInUserRequestDTO
import com.inhatc.Health_Application.api.user.http.req.SignUpUserRequestDTO
import com.inhatc.Health_Application.api.user.http.req.toCommand
import com.inhatc.Health_Application.domain.user.UserService
import org.springframework.stereotype.Component

@Component
class UserFacade (
    val userService: UserService
){
    fun signUp(request: SignUpUserRequestDTO): String {
        return userService.signUp(request.toCommand())
    }

    fun signIn(request: SignInUserRequestDTO): String {
        return userService.signIn(request.toCommand())
    }
}
