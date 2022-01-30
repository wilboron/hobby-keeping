package com.gmail.williammingardi.hobbykeeping.security

import com.gmail.williammingardi.hobbykeeping.domain.user.UserRepository
import com.gmail.williammingardi.hobbykeeping.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService : UserDetailsService {

    @Autowired
    private lateinit var repository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByUsername(username) ?: throw RuntimeException()
        return UserDetail(user)
    }
}