package com.gmail.williammingardi.hobbykeeping.domain.read

import com.gmail.williammingardi.hobbykeeping.domain.book.Book
import com.gmail.williammingardi.hobbykeeping.domain.user.User
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import javax.persistence.*


@Entity
@Table(name = "reads")
class Read(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: Book? = null,

    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime? = null,

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null,

    @Column(name = "did_not_finished")
    var didNotFinished: Boolean? = false,


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
        other as Read

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}