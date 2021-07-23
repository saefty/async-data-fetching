package de.saefty.comment

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "placeholder-api")
@Path("/comments")
interface CommentClient {
    @GET
    fun getAllComment(): List<Comment> = listOf()
    @QueryParam("userId")
    fun getCommentsByUserId(userId: String): List<Comment> = listOf()
}