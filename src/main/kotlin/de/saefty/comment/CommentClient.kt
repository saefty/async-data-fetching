package de.saefty.comment

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CommentClient {
    fun getAllComment(): List<Comment> = listOf()
    fun getCommentsByUserId(userId: String): List<Comment> = listOf()
}