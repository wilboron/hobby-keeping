package com.gmail.williammingardi.hobbykeeping.domain.user

import com.gmail.williammingardi.hobbykeeping.exception.NotFoundException
import com.gmail.williammingardi.hobbykeeping.security.UserDetail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun findAll(pagination: Pageable): Page<User> {
        return repository.findAll(pagination)
    }

    fun findById(id: Long): User {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("User", id)
    }

    fun save(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return repository.save(user)
    }

    fun update(id: Long, userUpdate: User): User {
        val user = repository.findByIdOrNull(id) ?: throw NotFoundException("User", id)
        user.name = userUpdate.name
        repository.save(user)
        return user
    }

    fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email)
    }

    fun existsByUsername(username: String): Boolean {
        return repository.existsByUsername(username)
    }

    fun findByUsername(username: String?): UserDetails {
        val user = repository.findByUsername(username) ?: throw RuntimeException()
        return UserDetail(user)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

}