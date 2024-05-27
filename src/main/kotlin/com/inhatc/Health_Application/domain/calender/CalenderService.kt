package com.inhatc.Health_Application.domain.calender

import com.inhatc.Health_Application.domain.calender.entity.Calender
import com.inhatc.Health_Application.domain.calender.model.ExerciseType
import com.inhatc.Health_Application.domain.calender.repository.CalenderRepository
import com.inhatc.Health_Application.domain.user.error.NotFoundUserException
import com.inhatc.Health_Application.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CalenderService(
    val calenderRepository: CalenderRepository,
    val userRepository: UserRepository,
) {
    @Transactional
    fun recordExercise(email: String, type: ExerciseType) {
        val checkUser = userRepository.findByEmail(email) ?: throw NotFoundUserException()

        Calender(
            userId = checkUser.userId!!,
            type = type,
            recordedAt = LocalDateTime.now()
        ).let { calenderRepository.save(it) }
    }
}
