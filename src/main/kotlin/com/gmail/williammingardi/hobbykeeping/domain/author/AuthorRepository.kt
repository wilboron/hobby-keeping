package com.gmail.williammingardi.hobbykeeping.domain.author

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long> {

}