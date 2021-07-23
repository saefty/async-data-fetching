package de.saefty.comment

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Post resource based on http://jsonplaceholder.typicode.com/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)