package com.example.spotify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("cover_id")
    val coverId: Int?,
    val title: String?,
    @SerialName("cover_edition_key")
    val coverEditionKey: String?,
    val authors: List<Author>?
)

@Serializable
data class BooksByCategory(
    val works: List<Book>
)

@Serializable
data class Author(
    val name: String
)