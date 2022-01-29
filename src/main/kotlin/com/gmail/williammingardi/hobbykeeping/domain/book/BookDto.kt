package com.gmail.williammingardi.hobbykeeping.domain.book

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class BookResponse(
    val id: Long?,
    val name: String?,
    @JsonProperty("author_name")
    val authorName: String?,
    @JsonProperty("num_pages")
    val numPages: Long?,
    val genre: String?,
    val serie: String?,
    @JsonProperty("publish_date")
    val publishDate: LocalDate?,
    @JsonProperty("created_at")
    val createdAt: OffsetDateTime?,
    @JsonProperty("updated_at")
    val updatedAt: OffsetDateTime?
)

data class CreateBookRequest(
    @field:Length(min = 2, max = 255)
    val name: String?,
    @JsonProperty("num_pages")
    @field:Min(1)
    val numPages: Long?,
    @field:Length(min = 2, max = 255)
    val genre: String?,
    val serie: String?,
    @JsonProperty("publish_date")
    val publishDate: LocalDate?,
    @field:Min(1)
    @JsonProperty("author_id")
    val authorId: Long,
)