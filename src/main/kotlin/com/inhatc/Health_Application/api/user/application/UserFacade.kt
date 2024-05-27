package com.inhatc.Health_Application.api.user.application

import com.inhatc.Health_Application.api.user.http.req.SignInUserRequestDTO
import com.inhatc.Health_Application.api.user.http.req.SignUpUserRequestDTO
import com.inhatc.Health_Application.api.user.http.req.toCommand
import com.inhatc.Health_Application.domain.user.UserService
import com.inhatc.Health_Application.domain.user.dto.UserDTO
import org.springframework.stereotype.Component

@Component
class UserFacade(
    val userService: UserService
) {
    fun signUp(request: SignUpUserRequestDTO): String {
        return userService.signUp(request.toCommand())
    }

    fun signIn(request: SignInUserRequestDTO): String {
        return userService.signIn(request.toCommand())
    }

    fun getUserDetail(email: String): UserDTO {
        return userService.getUserDetail(email)
    }

    fun modifyGoal(email: String, goalCount: Int) {
        return userService.modifyGoal(email, goalCount)
    }
}
