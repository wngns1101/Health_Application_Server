package com.inhatc.Health_Application.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme

import org.springdoc.core.customizers.OpenApiCustomizer
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val USER_V1 = "유저 API"
const val USER_V2_PREFIX = "/user/v1"
const val BOARD_V2_PREFIX = "/board/v1"

@OpenAPIDefinition(
    info = Info(title = "HealthCare Api 명세서", version = "v1")
)

@Configuration
class SwaggerConfig {
    @Bean
    fun v2Api(): GroupedOpenApi {
        val paths: Array<String> = arrayOf(
            "$USER_V2_PREFIX/**",
            "$BOARD_V2_PREFIX/**"
        )

        return GroupedOpenApi.builder()
            .group("v1")
            .pathsToMatch(*paths)
            .addOpenApiCustomizer {
                buildSecurityOpenApi(true)
            }
            .build()
    }

    fun buildSecurityOpenApi(active: Boolean): OpenApiCustomizer {
        val securityScheme = SecurityScheme()

        securityScheme.name = "Authorization"
        securityScheme.type = SecurityScheme.Type.HTTP
        securityScheme.`in` = SecurityScheme.In.HEADER
        securityScheme.bearerFormat = "JWT"
        securityScheme.scheme = "bearer"

        return OpenApiCustomizer {
            it.addSecurityItem(SecurityRequirement().addList("jwt token"))
            it.components.addSecuritySchemes("jwt token", securityScheme)
        }
    }
}
