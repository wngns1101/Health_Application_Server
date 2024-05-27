package com.inhatc.Health_Application.api.calender.application

import com.inhatc.Health_Application.domain.calender.CalenderService
import com.inhatc.Health_Application.domain.calender.model.ExerciseType
import org.springframework.stereotype.Component

@Component
class CalenderFacade(
    private val calenderService: CalenderService,
) {
    fun recordExercise(email: String, type: ExerciseType) {
        calenderService.recordExercise(email, type)
    }
}
