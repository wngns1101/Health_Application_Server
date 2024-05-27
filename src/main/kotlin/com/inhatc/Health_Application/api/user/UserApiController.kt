package com.inhatc.Health_Application.api.user

import com.inhatc.Health_Application.api.user.http.req.SignUpUserRequestDTO
import com.inhatc.Health_Application.api.user.application.UserFacade
import com.inhatc.Health_Application.api.user.http.req.SignInUserRequestDTO
import com.inhatc.Health_Application.config.USER_V2_PREFIX
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(USER_V2_PREFIX)
class UserApiController (
    val userFacade: UserFacade,
){
    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody request: SignUpUserRequestDTO
    ): String {
        return userFacade.signUp(request)
    }

    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody request: SignInUserRequestDTO
    ): String {
        return userFacade.signIn(request)
    }

    fun userDetail(

    ){

    }

    fun modifyUser(

    ){

    }

}