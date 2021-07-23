package de.saefty.post

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import javax.inject.Inject
import javax.ws.rs.WebApplicationException
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


@QuarkusTest
@QuarkusTestResource(PostPlaceHolderWiremock::class)
class PostClientTest {
    @Inject
    @RestClient
    lateinit var postClient: PostClient

    @Test
    fun `Given available backend, When data is fetched, Then posts are returned`() {
        val response = postClient.getPosts(null).await().indefinitely()
        assertEquals(2, response.size)
    }

    @Test
    fun `Given available backend, When data is fetched, Then post is returned`() {
        val response = postClient.getPosts(1).await().indefinitely()
        assertEquals(2, response.size)
    }

    @Test
    fun `Given available backend, When no data available is fetched, Then error is returned`() {
        val subscriber = postClient.getPosts(2).subscribe().withSubscriber(UniAssertSubscriber.create())
        subscriber.awaitFailure().assertFailedWith(WebApplicationException::class.java)
    }

}