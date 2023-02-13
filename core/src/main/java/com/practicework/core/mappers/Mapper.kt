package com.practicework.core.mappers

interface Mapper<I, O> {
    fun map(input: I) : O
}