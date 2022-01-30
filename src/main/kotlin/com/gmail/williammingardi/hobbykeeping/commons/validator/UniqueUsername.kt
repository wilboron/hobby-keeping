package com.gmail.williammingardi.hobbykeeping.commons.validator

import com.gmail.williammingardi.hobbykeeping.domain.user.UserService
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueUsernameValidator::class])
@MustBeDocumented
annotation class UniqueUsername(
    val message: String = "Username already taken",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

class UniqueUsernameValidator(
    private val userService: UserService
) : ConstraintValidator<UniqueUsername, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        return !userService.existsByUsername(value)
    }
}