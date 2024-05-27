package com.inhatc.Health_Application.api.calender

import com.inhatc.Health_Application.api.calender.application.CalenderFacade
import com.inhatc.Health_Application.api.calender.http.req.RecordExerciseRequestDTO
import com.inhatc.Health_Application.config.CALENDER_V1
import com.inhatc.Health_Application.config.CALENDER_V1_PREFIX
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = CALENDER_V1)
@RestController
@RequestMapping(CALENDER_V1_PREFIX)
class CalenderApiController(
    val calenderFacade: CalenderFacade,
) {
    @Operation(summary = "[달력-001] 운동 기록 API")
    @PostMapping("/calenders/record")
    fun recordExercise(
        @RequestAttribute("email") email: String,
        @RequestBody request: RecordExerciseRequestDTO,
    ) {
        calenderFacade.recordExercise(email, request.type)
    }
}
