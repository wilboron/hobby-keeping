package com.gmail.williammingardi.hobbykeeping.security

import com.gmail.williammingardi.hobbykeeping.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val user: User) : UserDetails {
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()
    
    override fun isEnabled() = true

    override fun getUsername() = user.username

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = user.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}