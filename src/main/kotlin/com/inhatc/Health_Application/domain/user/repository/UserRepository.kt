package com.inhatc.Health_Application.domain.user.repository

import com.inhatc.Health_Application.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}
