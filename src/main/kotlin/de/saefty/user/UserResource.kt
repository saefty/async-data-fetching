package de.saefty.user

import de.saefty.post.Post
import de.saefty.post.PostClient
import io.smallrye.common.annotation.Blocking
import io.smallrye.mutiny.Uni
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/users")
@Tag(name = "user")
class UserResource(
    @RestClient private val userClient: UserClient,
    @RestClient private val postClient: PostClient
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers(
        @QueryParam("included") @DefaultValue("") includedValues: String
    ): Uni<Set<User>> {
        return if ("posts" == includedValues) {
            val users = userClient.getAllUsers().onItem().transform {
                val userWithPosts = Uni.createFrom().item {
                    it.map {
                        it.posts = postClient.getPosts(it.id).await().indefinitely()
                    }
                    it
                }
                userWithPosts
            }
            users.flatMap { it }
        } else userClient.getAllUsers()
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    fun getUserById(
        @PathParam("userId") userId: Int,
        @QueryParam("included") @DefaultValue("") includedValues: String
    ): Uni<User> {
        return if ("posts" == includedValues) {
            Uni.combine().all().unis(
                userClient.getUserById(userId),
                postClient.getPosts(userId)
            ).combinedWith { result: List<*> ->
                val user = result[0] as User
                // second parameter is always of type Post
                @Suppress("UNCHECKED_CAST")
                user.posts = result[1] as Set<Post>
                user
            }
        } else userClient.getUserById(userId)
    }
}