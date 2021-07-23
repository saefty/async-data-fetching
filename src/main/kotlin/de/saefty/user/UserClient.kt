package de.saefty.user

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "placeholder-api")
@Path("/users")
interface UserClient {
    @GET
    fun getAllUsers(): List<Map<String, Any>>

    @GET
    fun getUserById(@QueryParam("userId") userId: String): Map<String, Any>
}