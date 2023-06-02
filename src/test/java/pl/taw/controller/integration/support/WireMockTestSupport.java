package pl.taw.controller.integration.support;

import com.github.tomakehurst.wiremock.WireMockServer;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public interface WireMockTestSupport {

    default void stubForPet(final WireMockServer wireMockServer, final Long petId) {
        wireMockServer.stubFor(get(urlEqualTo("/pet/%s".formatted(petId))).willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBodyFile("wiremock/petById.json")
                .withTransformerParameters(Map.of("petId", petId))
                .withTransformers("response-template")));
    }
}
