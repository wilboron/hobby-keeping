package com.gmail.williammingardi.hobbykeeping.domain.book

import com.gmail.williammingardi.hobbykeeping.commons.Mapper
import com.gmail.williammingardi.hobbykeeping.domain.author.AuthorService
import org.springframework.stereotype.Component

@Component
class BookResponseMapper : Mapper<Book, BookResponse> {
    override fun map(t: Book): BookResponse {
        return BookResponse(
            id = t.id,
            name = t.name,
            authorName = t.author?.name,
            numPages = t.numPages,
            genre = t.genre,
            serie = t.serie,
            publishDate = t.publishDate,
            createdAt = t.createdAt,
            updatedAt = t.updatedAt
        )
    }
}

@Component
class CreateBookRequestMapper(
    private val authorService: AuthorService
) : Mapper<CreateBookRequest, Book> {
    override fun map(t: CreateBookRequest): Book {
        return Book(
            name = t.name,
            numPages = t.numPages,
            genre = t.genre,
            serie = t.serie,
            publishDate = t.publishDate,
            author = authorService.findById(t.authorId)
        )
    }
}