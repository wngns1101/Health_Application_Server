package com.inhatc.Health_Application.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered

@Configuration
class ApiFilterConfig {
    private val includeTokenFilterPaths = arrayOf(
        "$USER_V1_PREFIX/*",
        "$CALENDER_V1_PREFIX/*"
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
