package com.gmail.williammingardi.hobbykeeping.domain.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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

@Tag(name = "User", description = "Manage users")
@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService,
    private val userResponseMapper: UserResponseMapper,
    private val createUserRequestMapper: CreateUserRequestMapper
) {

    // GET /users
    @Operation(summary = "Return all users in database")
    @GetMapping
    fun index(
        @ParameterObject pagination: Pageable
    ): Page<UserResponse> {
        return service.findAll(pagination).map { user ->
            userResponseMapper.map(user)
        }
    }

    // GET /users/1
    @Operation(summary = "Find user with {id} in database")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Found the user",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            ApiResponse(responseCode = "404", description = "User not found")]
    )
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): UserResponse {
        return userResponseMapper.map(
            service.findById(id)
        )
    }

    // POST /users
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
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