package com.gmail.williammingardi.hobbykeeping.domain.author

import com.gmail.williammingardi.hobbykeeping.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val repository: AuthorRepository
) {
    fun findAll(pagination: Pageable): Page<Author> {
        return repository.findAll(pagination)
    }

    fun findById(id: Long): Author {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("Author", id)
    }

    fun save(author: Author): Author {
        return repository.save(author)
    }

}