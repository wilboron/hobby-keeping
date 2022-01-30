package com.gmail.williammingardi.hobbykeeping.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        try {
            val (email, password) = ObjectMapper().readValue(
                request.inputStream,
                Credentials::class.java
            )
            val token = UsernamePasswordAuthenticationToken(email, password)
            return authManager.authenticate(token)
        } catch (e: Exception) {
            throw UsernameNotFoundException("Could not log in, invalid credentials!")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val username = (authResult.principal as UserDetail).username
        val token = jwtUtil.generateToken(username.toString())
        response.addHeader("Authorization", "Bearer $token")
    }
}