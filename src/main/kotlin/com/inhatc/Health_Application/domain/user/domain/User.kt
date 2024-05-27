package com.inhatc.Health_Application.domain.user.domain

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null,
    val email: String,
    val password: String,
    val goalCount: Int,
)