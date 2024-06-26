package com.inhatc.Health_Application.domain.user

import com.inhatc.Health_Application.config.TokenProvider
import com.inhatc.Health_Application.domain.user.dto.SignInCommand
import com.inhatc.Health_Application.domain.user.dto.SignUpCommandDTO
import com.inhatc.Health_Application.domain.user.dto.UserDTO
import com.inhatc.Health_Application.domain.user.entity.User
import com.inhatc.Health_Application.domain.user.error.AlreadySignUpException
import com.inhatc.Health_Application.domain.user.error.NotFoundUserException
import com.inhatc.Health_Application.domain.user.repository.UserRepository
import com.inhatc.Health_Application.error.PasswordMismatchException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    val userRepository: UserRepository,
    val tokenProvider: TokenProvider,
) {
    @Transactional
    fun signUp(
        command: SignUpCommandDTO,
    ): String {
        val checkUser = userRepository.findByEmail(command.email)
        if (checkUser != null) throw AlreadySignUpException()

        val user = User(
            email = command.email,
            password = command.password,
            nickname = command.nickname,
            goalCount = command.goalCount,
        ).let {
            userRepository.save(it)
        }

        return tokenProvider.create(user.email)
    }

    fun signIn(
        command: SignInCommand
    ): String {
        val checkUser = userRepository.findByEmail(command.email) ?: throw NotFoundUserException()

        if (checkUser.password != command.password) {
            throw PasswordMismatchException()
        }

        return tokenProvider.create(checkUser.email)
    }

    fun getUserDetail(
        email: String,
    ): UserDTO {
        val checkUser = userRepository.findByEmail(email) ?: throw NotFoundUserException()

        return UserDTO(
            userId = checkUser.userId!!,
            email = checkUser.email,
            nickname = checkUser.nickname,
            goalCount = checkUser.goalCount,
        )
    }

    @Transactional
    fun modifyGoal(email: String, goalCount: Int) {
        val checkUser = userRepository.findByEmail(email) ?: throw NotFoundUserException()
        checkUser.goalCount = goalCount
    }
}
