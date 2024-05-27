package com.inhatc.Health_Application.config

import com.inhatc.Health_Application.error.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.crypto.SecretKey

@Configuration
class TokenProvider (
    @Value("\${secret-key}")
    val baseKey: String,
    @Value("\${access-token-expire}")
    val accessTokenExpire: Long,
){
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(baseKey.toByteArray())

    fun create(userEmail: String): String {
        val date = Date()
        date.time += (accessTokenExpire)

        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setSubject(userEmail)
            .setIssuedAt(date)
            .compact()
    }

    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(baseKey.toByteArray())
            .parseClaimsJws(token).body
    }

    fun getUserEmailFromToken(token: String): String {
        val claims = getClaimsFromToken(token)
        return claims.get("email").toString()
    }

    fun validationAccessToken(accessToken: String) {
        val claims = getClaimsFromToken(accessToken)

        if (claims["tokenType"] != "accessToken") {
            throw InvalidTokenException()
        }
    }
}