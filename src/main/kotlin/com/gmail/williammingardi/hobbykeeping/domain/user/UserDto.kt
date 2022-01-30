package com.gmail.williammingardi.hobbykeeping.domain.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.gmail.williammingardi.hobbykeeping.commons.validator.FieldMatch
import com.gmail.williammingardi.hobbykeeping.commons.validator.UniqueEmail
import com.gmail.williammingardi.hobbykeeping.commons.validator.UniqueUsername
import org.hibernate.validator.constraints.Length
import org.springframework.lang.Nullable
import java.time.OffsetDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

data class UserResponse(
    val id: Long?,
    val username: String?,
    val surname: String?,
    val email: String?,
    val name: String?,
    val bio: String?,
    @JsonProperty("created_at")
    val createdAt: OffsetDateTime?,
    @JsonProperty("updated_at")
    val updatedAt: OffsetDateTime?
)

@FieldMatch.List(
    [
        FieldMatch(
            field = "password",
            fieldMatch = "confirmPassword",
            message = "Passwords do not match!"
        ),
        FieldMatch(
            field = "email",
            fieldMatch = "confirmEmail",
            message = "Email addresses do not match!"
        )
    ]
)
data class CreateUserRequest(
    @field:NotNull
    @field:Length(min = 2, max = 255)
    @field:UniqueUsername
    val username: String?,
    @field:NotNull
    @field:Length(min = 2, max = 255)
    val name: String?,
    @field:NotNull
    @field:Length(min = 2, max = 255)
    val surname: String?,
    @field:NotNull
    @field:Email
    @field:UniqueEmail
    val email: String?,
    @JsonProperty("confirm_email")
    @field:NotNull
    @field:Email
    val confirmEmail: String?,
    @field:NotNull
    @field:Length(min = 8, max = 255)
    val password: String?,
    @field:NotNull
    @JsonProperty("confirm_password")
    @field:Length(min = 8, max = 255)
    val confirmPassword: String?,
    @field:Length(min = 0, max = 1000)
    val bio: String?,
)