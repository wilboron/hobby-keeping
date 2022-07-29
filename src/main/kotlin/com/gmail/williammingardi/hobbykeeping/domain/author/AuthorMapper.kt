package com.gmail.williammingardi.hobbykeeping.domain.author

import com.gmail.williammingardi.hobbykeeping.commons.Mapper
import com.gmail.williammingardi.hobbykeeping.domain.book.BookResponseMapper
import org.springframework.stereotype.Component

@Component
class AuthorResponseMapper(
    private val bookResponseMapper: BookResponseMapper,
) : Mapper<Author, AuthorResponse> {
    override fun map(t: Author): AuthorResponse {
        return AuthorResponse(
            id = t.id,
            name = t.name,
            books = t.books.map { book -> bookResponseMapper.map(book) },
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