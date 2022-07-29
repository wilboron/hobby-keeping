package com.gmail.williammingardi.hobbykeeping.config

import com.github.javafaker.Faker
import com.gmail.williammingardi.hobbykeeping.domain.author.Author
import com.gmail.williammingardi.hobbykeeping.domain.author.AuthorService
import com.gmail.williammingardi.hobbykeeping.domain.book.Book
import com.gmail.williammingardi.hobbykeeping.domain.book.BookService
import com.gmail.williammingardi.hobbykeeping.domain.read.Read
import com.gmail.williammingardi.hobbykeeping.domain.read.ReadService
import com.gmail.williammingardi.hobbykeeping.domain.user.User
import com.gmail.williammingardi.hobbykeeping.domain.user.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneId
import java.util.concurrent.TimeUnit
import javax.transaction.Transactional

@RestController
@RequestMapping("/seed")
class PopulateDBController(
    private val authorService: AuthorService,
    private val userService: UserService,
    private val bookService: BookService,
    private val readService: ReadService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    val faker = Faker()

    // GET /authors
    @GetMapping
    fun index() {
        repeat(10) {
            val user = User(
                username = faker.name().username().split(".")[0],
                name = faker.name().name(),
                surname = faker.name().lastName(),
                email = faker.bothify("????##@gmail.com"),
                password = bCryptPasswordEncoder.encode("password"),
                bio = faker.harryPotter().quote()
            )
            userService.save(
                user
            )

            val author = Author(
                name = faker.book().author()
            )
            authorService.save(author)

            repeat(10) {
                val book = Book(
                    name = faker.book().title(),
                    numPages = faker.number().numberBetween(50, 500),
                    genre = faker.book().genre(),
                    serie = faker.lorem().characters(5, 20),
                    publishDate = faker.date().past(50 * 360, TimeUnit.DAYS).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                    author = author
                )
                bookService.save(
                    book
                )
                readService.save(
                    Read(
                        user = user,
                        book = book,
                        startDate = faker.date().past(3 * 360, TimeUnit.DAYS).toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toOffsetDateTime(),
                        endDate = faker.date().past(50, TimeUnit.DAYS).toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toOffsetDateTime(),
                        didNotFinished = false
                    )
                )
            }
        }
    }

    @DeleteMapping
    @Transactional
    fun delete() {
        readService.deleteAll()
        bookService.deleteAll()
        authorService.deleteAll()
        userService.deleteAll()
    }
}