package com.gmail.williammingardi.hobbykeeping.domain.author

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import java.time.OffsetDateTime
import javax.validation.constraints.Size

data class AuthorResponse(
    val id: Long?,
    val name: String?,
    @JsonProperty("created_at")
    val createdAt: OffsetDateTime?,
    @JsonProperty("updated_at")
    val updatedAt: OffsetDateTime?
)

data class CreateAuthorRequest(
    @field:Length(min = 2, max = 255)
    val name: String?,
)