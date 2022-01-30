package com.gmail.williammingardi.hobbykeeping.commons.validator

import com.gmail.williammingardi.hobbykeeping.domain.user.UserService
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueEmailValidator::class])
@MustBeDocumented
annotation class UniqueEmail(
    val message: String = "Email already taken",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

class UniqueEmailValidator(
    private val userService: UserService
) : ConstraintValidator<UniqueEmail, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        return !userService.existsByEmail(value)
    }
}