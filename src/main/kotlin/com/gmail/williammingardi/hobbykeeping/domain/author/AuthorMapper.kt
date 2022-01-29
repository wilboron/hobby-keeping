package com.gmail.williammingardi.hobbykeeping.domain.author

import com.gmail.williammingardi.hobbykeeping.commons.Mapper
import org.springframework.stereotype.Component

@Component
class AuthorResponseMapper : Mapper<Author, AuthorResponse> {
    override fun map(t: Author): AuthorResponse {
        return AuthorResponse(
            id = t.id,
            name = t.name,
            createdAt = t.createdAt,
            updatedAt = t.updatedAt
        )
    }
}

@Component
class CreateAuthorRequestMapper : Mapper<CreateAuthorRequest, Author> {
    override fun map(t: CreateAuthorRequest): Author {
        return Author(
            name = t.name
        )
    }
}