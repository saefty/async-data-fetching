package de.saefty.comment

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "placeholder-api")
@Path("/posts")
interface CommentClient {
    @GET
    fun getComments(@QueryParam("userId") userId: String?): List<Map<String, Any>>
}