package com.gmail.williammingardi.hobbykeeping.commons

interface Mapper<T, U> {
    fun map(t: T): U
}
