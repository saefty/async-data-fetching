package de.saefty.user

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path("/users")
@Tag(name = "user")
class UserResource(
    private val userClient: UserClient
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers() = userClient.getAllUsers()

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserById(@PathParam("userId") userId: String) = userClient.getUserById(userId)
}