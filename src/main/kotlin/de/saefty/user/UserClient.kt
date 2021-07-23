package de.saefty.user

import io.smallrye.mutiny.Uni
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

/**
 * Fetches users.
 * Read only.
 */
@RegisterRestClient
@Path("/users")
interface UserClient {
    @GET
    @Produces("application/json")
    fun getAllUsers(): Uni<Set<User>>

    @GET
    @Path("/{userId}")
    @Produces("application/json")
    fun getUserById(@PathParam("userId") userId: Any): Uni<User>
}