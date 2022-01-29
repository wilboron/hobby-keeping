package com.gmail.williammingardi.hobbykeeping.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ApplicationErrorResponse {
        return ApplicationErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath,
            method = request.method
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ApplicationErrorResponse {
        val errorMessage =
            exception.bindingResult.fieldErrors.associateBy(
                { it.field }, { it.defaultMessage }
            )
        return ApplicationErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath,
            method = request.method
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleArgumentTypeValidationError(
        exception: MethodArgumentTypeMismatchException,
        request: HttpServletRequest
    ): ApplicationErrorResponse {
        val message =
            """
                Param '${exception.name}' must be type ${exception.requiredType}. 
                Got '${exception.value}'
            """.trimIndent().replace("\n", "")
        return ApplicationErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = message,
            path = request.servletPath,
            method = request.method
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exception: Exception,
        request: HttpServletRequest
    ): ApplicationErrorResponse {
        return ApplicationErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = "Class: ${exception.javaClass.name} Error: ${exception.message}",
            path = request.servletPath,
            method = request.method
        )
    }
}