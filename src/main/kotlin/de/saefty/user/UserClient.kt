package de.saefty.user

import io.smallrye.mutiny.Uni
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

/**
 * Fetches users.
 * Read only.
 */
@RegisterRestClient(configKey = "placeholder-api")
@Path("/users")
interface UserClient {
    @GET
    fun getAllUsers(): Uni<List<User>>

    @GET
    @Path("/{userId}")
    fun getUserById(@PathParam("userId") userId: Any): Uni<User>
}