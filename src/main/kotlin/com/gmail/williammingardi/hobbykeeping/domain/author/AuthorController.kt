package com.gmail.williammingardi.hobbykeeping.domain.author

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/authors")
class AuthorController(
    private val service: AuthorService,
    private val authorResponseMapper: AuthorResponseMapper,
    private val createAuthorRequestMapper: CreateAuthorRequestMapper
) {

    // GET /authors
    @GetMapping
    fun index(
        pagination: Pageable
    ): Page<AuthorResponse> {
        return service.findAll(pagination).map { author ->
            authorResponseMapper.map(author)
        }
    }

    // GET /authors/1
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): AuthorResponse {
        return authorResponseMapper.map(
            service.findById(id)
        )
    }

    // POST /authors
    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid createRequest: CreateAuthorRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AuthorResponse> {
        val author = createAuthorRequestMapper.map(createRequest)
        val authorResponse = authorResponseMapper.map(service.save(author))
        val uri = uriBuilder.path("/authors/${authorResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(authorResponse)
    }

    // PATCH /authors/1
    @PatchMapping("/{id}")
    @Transactional
    fun update(@PathVariable id: Long) = id

    // DELETE /authors/1
    @DeleteMapping
    @Transactional
    fun destroy(@PathVariable id: Long) = id
}