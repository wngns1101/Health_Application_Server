package com.inhatc.Health_Application.domain.calender.entity

import com.inhatc.Health_Application.domain.calender.model.ExerciseType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "calender")
class Calender(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val calenderId: Long? = null,
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val type: ExerciseType,
    val recordedAt: LocalDateTime,
)
