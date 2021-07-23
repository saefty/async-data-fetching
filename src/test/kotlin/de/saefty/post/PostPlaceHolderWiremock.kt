package de.saefty.post

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import java.util.Collections
import org.acme.getting.started.country.PlaceHolderApiWiremock

class PostPlaceHolderWiremock : PlaceHolderApiWiremock() {
    override fun start(): MutableMap<String, String> {
        super.start()
        wireMockServer.stubFor(
            get(urlEqualTo("/posts"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("posts.json")
                )
        )
        wireMockServer.stubFor(
            get(urlEqualTo("/posts?userId=1"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("posts.json")
                )
        )
        return Collections.singletonMap(
            "de.saefty.post.PostClient/mp-rest/url",
            wireMockServer.baseUrl()
        )
    }
}