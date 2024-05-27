package com.inhatc.Health_Application.domain.calender.repository

import com.inhatc.Health_Application.domain.calender.entity.Calender
import org.springframework.data.jpa.repository.JpaRepository

interface CalenderRepository : JpaRepository<Calender, Long>
