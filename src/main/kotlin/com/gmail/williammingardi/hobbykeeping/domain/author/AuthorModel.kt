package com.gmail.williammingardi.hobbykeeping.domain.author

import com.gmail.williammingardi.hobbykeeping.domain.book.Book
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "authors")
class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
    var books: MutableSet<Book> = mutableSetOf(),

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: OffsetDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
)