package com.space.core.domain.common

interface BaseMapper<in MODEL_A, out MODEL_B> {
    fun map(input: MODEL_A): MODEL_B
}