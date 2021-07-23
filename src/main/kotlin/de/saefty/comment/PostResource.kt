package de.saefty.comment

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/posts")
@Tag(name = "comment")
class PostResource(
    @RestClient private val postClient: PostClient
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getComments(@QueryParam("userId") userId: String?): List<Post> = postClient.getComments(userId)
}