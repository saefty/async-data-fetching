package de.saefty.post

import io.smallrye.mutiny.Uni
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

/**
 * Fetches postings on website.
 * Read only.
 */
@RegisterRestClient
@Path("/posts")
interface PostClient {
    @GET
    fun getPosts(@QueryParam("userId") userId: Int? = null): Uni<Set<Post>>
}