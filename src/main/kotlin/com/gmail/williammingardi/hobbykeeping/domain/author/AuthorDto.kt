package com.gmail.williammingardi.hobbykeeping.domain.author

import com.fasterxml.jackson.annotation.JsonProperty
import com.gmail.williammingardi.hobbykeeping.domain.book.BookResponse
import org.hibernate.validator.constraints.Length
import java.time.OffsetDateTime

data class AuthorResponse(
    val id: Long?,
    val name: String?,
    val books: List<BookResponse>,
    @JsonProperty("created_at")
    val createdAt: OffsetDateTime?,
    @JsonProperty("updated_at")
    val updatedAt: OffsetDateTime?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuthorResponse

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}

data class CreateAuthorRequest(
    @field:Length(min = 2, max = 255)
    val name: String?,
)