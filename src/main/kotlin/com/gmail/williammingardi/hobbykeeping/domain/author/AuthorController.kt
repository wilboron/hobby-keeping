package com.gmail.williammingardi.hobbykeeping.domain.author

import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@Tag(name = "Author", description = "Manage authors")
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
        @ParameterObject pagination: Pageable
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
    @ResponseStatus(HttpStatus.CREATED)
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
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid updateRequest: CreateAuthorRequest
    ): AuthorResponse {
        val author = createAuthorRequestMapper.map(updateRequest)
        return authorResponseMapper.map(service.update(id, author))
    }

    // DELETE /authors/1
    @DeleteMapping
    @Transactional
    fun destroy(@PathVariable id: Long) = id
}