package com.gmail.williammingardi.hobbykeeping.domain.book

import com.gmail.williammingardi.hobbykeeping.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    private val repository: BookRepository
) {
    fun findAll(pagination: Pageable): Page<Book> {
        return repository.findAll(pagination)
    }

    fun findById(id: Long): Book {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("Book", id)
    }

    fun save(book: Book): Book {
        return repository.save(book)
    }

    fun update(id: Long, bookUpdate: Book): Book {
        val book = repository.findByIdOrNull(id) ?: throw NotFoundException("Book", id)
        book.genre = bookUpdate.genre
        repository.save(book)
        return book
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}