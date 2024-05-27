package com.inhatc.Health_Application.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.inhatc.Health_Application.error.ErrorModel
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class ApiFilter(
    private val tokenManager: TokenProvider,
) : Filter {
    val logger = LoggerFactory.getLogger(this::class.java)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req: HttpServletRequest = request as HttpServletRequest
        val res: HttpServletResponse = response as HttpServletResponse

        var accessToken = req.getHeader("Authorization")

        if (req.requestURI == "$USER_V1_PREFIX/sign-up") {
            chain.doFilter(req, res)
            return
        }

        if (req.requestURI == "$USER_V1_PREFIX/sign-in") {
            chain.doFilter(req, res)
            return
        }

        if (accessToken != null) {
            accessToken = try {
                accessToken.split(" ")[1]
            } catch (e: Exception) {
                sendError(res, HttpStatus.UNAUTHORIZED)
                return
            }
        }

        accessToken = try {
            accessToken.split(" ")[0]
        } catch (e: Exception) {
            sendError(res, HttpStatus.UNAUTHORIZED)
            return
        }

        tokenManager.validationAccessToken(accessToken)

        val email = tokenManager.getUserEmailFromToken(accessToken)
        req.setAttribute("email", email)

        chain.doFilter(req, res)
    }

    private fun sendError(res: HttpServletResponse, status: HttpStatus) {
        val error = ErrorModel(code = status.value(), message = status.name)
        res.status = status.value()
        res.contentType = MediaType.APPLICATION_JSON_VALUE
        res.characterEncoding = "UTF-8"

        res.setHeader("Access-Control-Allow-Origin", "*")
        res.setHeader("Access-Control-Allow-Methods", "*")
        res.setHeader("Access-Control-Allow-Headers", "*")

        val jsonError = jacksonObjectMapper().writeValueAsString(error)
        res.writer.write(jsonError)
    }
}
