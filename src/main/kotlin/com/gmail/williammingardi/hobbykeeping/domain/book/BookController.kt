package com.gmail.williammingardi.hobbykeeping.domain.book

import com.gmail.williammingardi.hobbykeeping.domain.author.CreateAuthorRequestMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/books")
class BookController(
    private val service: BookService,
    private val bookResponseMapper: BookResponseMapper,
    private val createBookRequestMapper: CreateBookRequestMapper
) {

    // GET /books
    @GetMapping
    fun index(
        pagination: Pageable
    ): Page<BookResponse> {
        return service.findAll(pagination).map { book ->
            bookResponseMapper.map(book)
        }
    }

    // GET /books/1
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): BookResponse {
        return bookResponseMapper.map(
            service.findById(id)
        )
    }

    // POST /books
    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid createRequest: CreateBookRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<BookResponse> {
        val book = createBookRequestMapper.map(createRequest)
        val bookResponse = bookResponseMapper.map(service.save(book))
        val uri = uriBuilder.path("/books/${bookResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(bookResponse)
    }

    // PATCH /books/1
    @PatchMapping("/{id}")
    @Transactional
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid updateRequest: CreateBookRequest
    ): BookResponse {
        val book = createBookRequestMapper.map(updateRequest)
        return bookResponseMapper.map(service.update(id, book))
    }

    // DELETE /books/1
    @DeleteMapping
    @Transactional
    fun destroy(@PathVariable id: Long) = id
}