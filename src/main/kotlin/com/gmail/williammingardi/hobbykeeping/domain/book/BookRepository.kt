package com.gmail.williammingardi.hobbykeeping.domain.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

}