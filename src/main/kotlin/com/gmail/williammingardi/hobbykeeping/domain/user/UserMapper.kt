package com.gmail.williammingardi.hobbykeeping.domain.user

import com.gmail.williammingardi.hobbykeeping.commons.Mapper
import com.gmail.williammingardi.hobbykeeping.domain.author.AuthorService
import org.springframework.stereotype.Component

@Component
class UserResponseMapper : Mapper<User, UserResponse> {
    override fun map(t: User): UserResponse {
        return UserResponse(
            id = t.id,
            username = t.username,
            name = t.name,
            surname = t.surname,
            email = t.email,
            bio = t.bio,
            createdAt = t.createdAt,
            updatedAt = t.updatedAt
        )
    }
}

@Component
class CreateUserRequestMapper(
    private val authorService: AuthorService
) : Mapper<CreateUserRequest, User> {
    override fun map(t: CreateUserRequest): User {
        return User(
            username = t.username,
            name = t.name,
            surname = t.surname,
            email = t.email,
            password = t.password,
            bio = t.bio
        )
    }
}