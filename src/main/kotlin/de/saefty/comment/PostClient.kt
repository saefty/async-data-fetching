package de.saefty.comment

import io.smallrye.mutiny.Multi
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

/**
 * Fetches postings on website.
 * Read only.
 */
@RegisterRestClient(configKey = "placeholder-api")
@Path("/posts")
interface PostClient {
    @GET
    fun getComments(@QueryParam("userId") userId: String?): Multi<Post>
}