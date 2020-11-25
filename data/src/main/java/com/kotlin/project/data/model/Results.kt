package com.kotlin.project.data.model

data class Results(
    val url: String,
    val name: String,
    val image: String
)

fun PokemonListQuery.Result.transform(): Results {
    return Results(
        url = url().toString(),
        name = name().toString(),
        image = image().toString()
    )
}
