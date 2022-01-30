package com.gmail.williammingardi.hobbykeeping.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String?): User?
}