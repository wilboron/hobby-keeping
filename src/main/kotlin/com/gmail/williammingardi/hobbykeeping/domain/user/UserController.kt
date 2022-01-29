package com.gmail.williammingardi.hobbykeeping.domain.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService,
    private val userResponseMapper: UserResponseMapper,
    private val createUserRequestMapper: CreateUserRequestMapper
) {

    // GET /users
    @GetMapping
    fun index(
        pagination: Pageable
    ): Page<UserResponse> {
        return service.findAll(pagination).map { user ->
            userResponseMapper.map(user)
        }
    }

    // GET /users/1
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): UserResponse {
        return userResponseMapper.map(
            service.findById(id)
        )
    }

    // POST /users
    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid createRequest: CreateUserRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserResponse> {
        val user = createUserRequestMapper.map(createRequest)
        val userResponse = userResponseMapper.map(service.save(user))
        val uri = uriBuilder.path("/users/${userResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(userResponse)
    }

    // PATCH /users/1
    @PatchMapping("/{id}")
    @Transactional
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid updateRequest: CreateUserRequest
    ): UserResponse {
        val user = createUserRequestMapper.map(updateRequest)
        return userResponseMapper.map(service.update(id, user))
    }

    // DELETE /users/1
    @DeleteMapping
    @Transactional
    fun destroy(@PathVariable id: Long) = id
}