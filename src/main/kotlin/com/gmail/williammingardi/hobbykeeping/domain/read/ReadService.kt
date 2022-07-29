package com.gmail.williammingardi.hobbykeeping.domain.read

import com.gmail.williammingardi.hobbykeeping.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReadService(
    private val repository: ReadRepository
) {
    fun findAll(pagination: Pageable): Page<Read> {
        return repository.findAll(pagination)
    }

    fun findById(id: Long): Read {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("Book", id)
    }

    fun save(read: Read): Read {
        return repository.save(read)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

//    fun update(id: Long, bookUpdate: Read): Read {
//        val book = repository.findByIdOrNull(id) ?: throw NotFoundException("Book", id)
//        book.genre = bookUpdate.genre
//        repository.save(book)
//        return book
//    }
}