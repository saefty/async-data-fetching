package de.saefty.user

import io.smallrye.common.annotation.Blocking
import io.smallrye.mutiny.Uni
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/users")
@Tag(name = "user")
class UserResource(
    @RestClient private val userClient: UserClient
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers(): Uni<List<User>> = userClient.getAllUsers()

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    fun getUserById(@PathParam("userId") userId: String): Uni<User> = userClient.getUserById(userId)
}