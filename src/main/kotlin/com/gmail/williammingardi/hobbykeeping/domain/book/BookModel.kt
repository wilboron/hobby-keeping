package com.gmail.williammingardi.hobbykeeping.domain.book

import com.gmail.williammingardi.hobbykeeping.domain.author.Author
import com.gmail.williammingardi.hobbykeeping.domain.read.Read
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "books")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "num_pages", nullable = false)
    var numPages: Long? = null,

    @Column(name = "genre", nullable = false)
    var genre: String? = null,

    @Column(name = "serie")
    var serie: String? = null,

    @Column(name = "publish_date", nullable = false)
    var publishDate: LocalDate? = null,

    @OneToMany(mappedBy = "book", cascade = [CascadeType.ALL], orphanRemoval = true)
    var reads: MutableSet<Read> = mutableSetOf(),

    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    var author: Author? = null,

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: OffsetDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(
                other
            )
        ) return false
        other as Book

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}