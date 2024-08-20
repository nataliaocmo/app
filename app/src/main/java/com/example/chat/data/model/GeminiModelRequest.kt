package com.example.chat.data.model

data class Part(
    val text: String
)

data class Content(
    val parts: List<Part>
)

data class PostRequest(
    val contents: List<Content>
)
