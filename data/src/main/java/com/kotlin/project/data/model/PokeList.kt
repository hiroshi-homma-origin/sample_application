package com.kotlin.project.data.model

data class PokeList(
    val count: Int,
    val next: String,
    val previous: String?,
    val status: Boolean,
    val message: String,
    val results: Results
)
