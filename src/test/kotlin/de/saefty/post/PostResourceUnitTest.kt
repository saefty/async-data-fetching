package de.saefty.post

import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import javax.ws.rs.WebApplicationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class PostResourceUnitTest {
    private val postClient: PostClient = mock {}

    private val classUnderTest: PostResource = PostResource(postClient)


    private val setOfMockPosts = (0..1000).map {
        Post(
            it,
            it * 2,
            "lorem",
            "lorem ipsum"
        )
    }.toSet()

    @Test
    fun `Given reactive list of posts, When all posts are fetched, Then uni of posts returned`() {
        Mockito.`when`(postClient.getPosts(anyOrNull())).thenReturn(Uni.createFrom().item(setOfMockPosts))

        val result = classUnderTest.getComments(null).subscribe().withSubscriber(
            UniAssertSubscriber.create()
        )
        val resultItems = result.assertCompleted().item

        Assertions.assertEquals(setOfMockPosts, resultItems)
    }

    @Test
    fun `Given reactive list of posts, When posts are fetched by user id, Then uni of posts returned`() {
        val mockUserId = 1
        Mockito.`when`(postClient.getPosts(mockUserId)).thenReturn(Uni.createFrom().item(setOfMockPosts))

        val result = classUnderTest.getComments(mockUserId).subscribe().withSubscriber(
            UniAssertSubscriber.create()
        )
        val resultItems = result.assertCompleted().item

        Assertions.assertEquals(setOfMockPosts, resultItems)
    }


    @Test
    fun `Given failed uni of user, When all users are fetched, Then uni fails`() {
        Mockito.`when`(postClient.getPosts(anyOrNull())).thenReturn(
            Uni.createFrom().failure(WebApplicationException(404))
        )

        val result = classUnderTest.getComments(null).subscribe().withSubscriber(
            UniAssertSubscriber.create()
        )
        val resultItems = result.assertFailedWith(WebApplicationException::class.java).failure
        Assertions.assertEquals("HTTP 404 Not Found", resultItems.message)
    }
}