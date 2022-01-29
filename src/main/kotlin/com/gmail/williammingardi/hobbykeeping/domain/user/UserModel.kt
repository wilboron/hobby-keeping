package com.gmail.williammingardi.hobbykeeping.domain.user

import com.gmail.williammingardi.hobbykeeping.domain.read.Read
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "username", nullable = false, unique = true)
    var username: String? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @Column(name = "surname", nullable = false)
    var surname: String? = null,

    @Column(name = "email", nullable = false, unique = true)
    var email: String? = null,

    @Column(name = "password", nullable = false)
    var password: String? = null,

    @Column(name = "bio")
    var bio: String? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var reads: MutableSet<Read> = mutableSetOf(),

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: OffsetDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
)