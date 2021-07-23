package de.saefty.comment

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path("/comments")
@Tag(name = "comment")
class CommentResource(
    private val commentClient: CommentClient
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllComments() = commentClient.getAllComment()

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserById(@QueryParam("userId") userId: String) =
        commentClient.getCommentsByUserId(userId)
}