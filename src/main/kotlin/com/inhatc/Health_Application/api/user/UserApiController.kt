package com.inhatc.Health_Application.api.user

import com.inhatc.Health_Application.api.user.application.UserFacade
import com.inhatc.Health_Application.api.user.http.req.ModifyGoalRequestDTO
import com.inhatc.Health_Application.api.user.http.req.SignInUserRequestDTO
import com.inhatc.Health_Application.api.user.http.req.SignUpUserRequestDTO
import com.inhatc.Health_Application.api.user.http.res.SignInUserResponseDTO
import com.inhatc.Health_Application.api.user.http.res.SignUpUserResponseDTO
import com.inhatc.Health_Application.api.user.http.res.UserDetailResponseDTO
import com.inhatc.Health_Application.config.USER_V1
import com.inhatc.Health_Application.config.USER_V1_PREFIX
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = USER_V1)
@RestController
@RequestMapping(USER_V1_PREFIX)
class UserApiController(
    val userFacade: UserFacade,
) {
    @Operation(summary = "[유저-001] 회원가입")
    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody request: SignUpUserRequestDTO
    ): SignUpUserResponseDTO {
        return SignUpUserResponseDTO(userFacade.signUp(request))
    }

    @Operation(summary = "[유저-002] 로그인")
    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody request: SignInUserRequestDTO
    ): SignInUserResponseDTO {
        return SignInUserResponseDTO(userFacade.signIn(request))
    }

    @Operation(summary = "[유저-003] 유저 상세 조회")
    @GetMapping("/users/me")
    fun userDetail(
        @RequestAttribute("email") email: String,
    ): UserDetailResponseDTO {
        return UserDetailResponseDTO(userFacade.getUserDetail(email))
    }

    @Operation(summary = "[유저-004] 목표 수정")
    @PostMapping("/users/me/modify-goal")
    fun modifyGoalCount(
        @RequestAttribute("email") email: String,
        @RequestBody request: ModifyGoalRequestDTO
    ) {
        userFacade.modifyGoal(email, request.goalCount)
    }
}
