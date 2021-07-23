package org.acme.getting.started.country

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager


open class PlaceHolderApiWiremock : QuarkusTestResourceLifecycleManager {

    lateinit var wireMockServer: WireMockServer

    override fun start(): MutableMap<String, String> {
        wireMockServer = WireMockServer(options().dynamicPort())
        wireMockServer.start()

        return mutableMapOf()
    }

    override fun stop() {
        wireMockServer.stop()
    }

}