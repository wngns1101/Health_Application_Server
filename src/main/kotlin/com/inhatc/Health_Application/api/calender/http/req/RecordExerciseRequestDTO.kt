package com.inhatc.Health_Application.api.calender.http.req

import com.inhatc.Health_Application.domain.calender.model.ExerciseType

data class RecordExerciseRequestDTO(
    val type: ExerciseType,
)
