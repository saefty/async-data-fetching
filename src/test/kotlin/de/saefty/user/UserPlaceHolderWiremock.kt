package de.saefty.user

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import java.util.Collections
import org.acme.getting.started.country.PlaceHolderApiWiremock

class UserPlaceHolderWiremock : PlaceHolderApiWiremock() {
    override fun start(): MutableMap<String, String> {
        super.start()
        wireMockServer.stubFor(
            get(urlEqualTo("/users"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("users.json")
                )
        )
        wireMockServer.stubFor(
            get(urlEqualTo("/users/1"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("user.json")
                )
        )
        return Collections.singletonMap(
            "de.saefty.user.UserClient/mp-rest/url",
            wireMockServer.baseUrl()
        )
    }
}