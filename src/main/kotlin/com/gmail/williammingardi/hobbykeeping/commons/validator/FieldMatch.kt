package com.gmail.williammingardi.hobbykeeping.commons.validator
import org.springframework.beans.BeanWrapperImpl
import kotlin.reflect.KClass
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Constraint(validatedBy = [FieldMatchValidator::class])
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class FieldMatch(
    val message: String = "Fields values don't match!",
    val field: String,
    val fieldMatch: String,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
) {
    @Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    @MustBeDocumented
    annotation class List(val value: Array<FieldMatch>)
}

class FieldMatchValidator : ConstraintValidator<FieldMatch, Any?> {
    private var field: String? = null
    private var fieldMatch: String? = null
    override fun initialize(constraintAnnotation: FieldMatch) {

        field = constraintAnnotation.field
        fieldMatch = constraintAnnotation.fieldMatch
    }

    override fun isValid(
        value: Any?,
        context: ConstraintValidatorContext
    ): Boolean {
        val fieldValue = BeanWrapperImpl(value!!)
            .getPropertyValue(field!!)
        val fieldMatchValue = BeanWrapperImpl(value)
            .getPropertyValue(fieldMatch!!)
        return if (fieldValue != null) {
            fieldValue == fieldMatchValue
        } else {
            fieldMatchValue == null
        }
    }
}