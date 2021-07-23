package de.saefty.user

import com.nhaarman.mockitokotlin2.mock
import de.saefty.post.Post
import de.saefty.post.PostClient
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import javax.ws.rs.WebApplicationException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class UserResourceUnitTest {

    private val userClient: UserClient = mock {}

    private val postClient: PostClient = mock {}

    private val classUnderTest: UserResource = UserResource(userClient, postClient)


    private val setOfMockPosts = (0..1000).map {
        Post(
            it,
            it * 2,
            "lorem",
            "lorem ipsum"
        )
    }.toSet()

    private val setOfMockUsers = (0..9).map {
        User(
            it,
            "lorem$it",
            "ipsum$it",
            "lorem$it@ipsum.de",
            "01231234234$it",
            Address("", "", "", "", Geo("", "")),
            Company("", "", ""),
            setOfMockPosts.map { post -> post.copy(body = post.body + "$it") }.toSet()
        )
    }.toSet()


    @Test
    fun `Given reactive list of user, When all users are fetched, Then uni of users returned`() {
        `when`(userClient.getAllUsers()).thenReturn(Uni.createFrom().item(setOfMockUsers))

        val result = classUnderTest.getAllUsers("").subscribe().withSubscriber(
            UniAssertSubscriber.create()
        )
        val resultItems = result.assertCompleted().item

        assertEquals(setOfMockUsers, resultItems)
    }

    @Test
    fun `Given reactive list of user and comments, When all users and comments are fetched, Then uni of users and comments returned`() {
        `when`(userClient.getAllUsers()).thenReturn(Uni.createFrom().item(setOfMockUsers))
        setOfMockUsers.forEach {
            `when`(postClient.getPosts(it.id)).thenReturn(Uni.createFrom().item(it.posts))
        }

        val result = classUnderTest.getAllUsers("posts").subscribe().withSubscriber(
            UniAssertSubscriber.create()
        )
        val resultItems = result.assertCompleted().item

        assertEquals(setOfMockUsers, resultItems)
        resultItems.forEach {
            assertEquals(it.posts, it.posts)
        }
    }


    @Test
    fun `Given failed uni of user, When all users are fetched, Then uni fails`() {
        `when`(userClient.getAllUsers()).thenReturn(
            Uni.createFrom().failure(WebApplicationException(404))
        )

        val result = classUnderTest.getAllUsers("").subscribe().withSubscriber(
            UniAssertSubscriber.create()
        )
        val resultItems = result.assertFailedWith(WebApplicationException::class.java).failure
        assertEquals("HTTP 404 Not Found", resultItems.message)
    }
}