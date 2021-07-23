package de.saefty.user

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import javax.inject.Inject
import javax.ws.rs.WebApplicationException
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


@QuarkusTest
@QuarkusTestResource(UserPlaceHolderWiremock::class)
class UserClientTest {
    @Inject
    @RestClient
    lateinit var userService: UserClient

    @Test
    fun `Given available backend, When data is fetched, Then users are returned`() {
        val response = userService.getAllUsers().await().indefinitely()
        assertEquals(2, response.size)
    }

    @Test
    fun `Given available backend, When data is fetched, Then user is returned`() {
        val response = userService.getUserById(1).await().indefinitely()
        assertEquals(1, response.id)
    }

    @Test
    fun `Given available backend, When no data available is fetched, Then error is returned`() {
        val subscriber = userService.getUserById(2).subscribe().withSubscriber(UniAssertSubscriber.create())
        subscriber.awaitFailure().assertFailedWith(WebApplicationException::class.java)
    }

}