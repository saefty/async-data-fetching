package de.saefty.comment

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "placeholder-api")
@Path("/posts")
interface PostClient {
    @GET
    fun getComments(@QueryParam("userId") userId: String?): List<Post>
}