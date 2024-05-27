package com.inhatc.Health_Application.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered

class ApiFilterConfig {
    private val includeTokenFilterPaths = arrayOf(
        "$USER_V2_PREFIX/*",
        "$BOARD_V2_PREFIX/*"
    )
    @Bean
    fun tokenFilter(tokenProvider: TokenProvider): FilterRegistrationBean<ApiFilter> {
        val registrationBean = FilterRegistrationBean(ApiFilter(tokenProvider))
        registrationBean.order = Integer.MIN_VALUE
        registrationBean.addUrlPatterns(*includeTokenFilterPaths)
        registrationBean.order = Ordered.HIGHEST_PRECEDENCE
        return registrationBean
    }
}