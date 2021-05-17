package com.assignment.spring.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;

public class MockUtil {

    public static void setupMockWeatherResponse(WireMockServer mockService, String city, String apiKey, int errorCode) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/data/2.5/weather?q="+city+"&appid="+apiKey))
                .willReturn(WireMock.aResponse()
                        .withStatus(errorCode)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                StreamUtils.copyToString(MockUtil.class.getClassLoader().getResourceAsStream("payload/get-weather-"+errorCode+"-response.json"),
                                        defaultCharset()))));
    }


    public static void setupMockWeatherResponseFor404(WireMockServer mockService, String city, String apiKey, int errorCode) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/data/2.5/weather?q&appid="+apiKey))
                .willReturn(WireMock.aResponse()
                        .withStatus(errorCode)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                StreamUtils.copyToString(MockUtil.class.getClassLoader().getResourceAsStream("payload/get-weather-"+errorCode+"-response.json"),
                                        defaultCharset()))));
    }



}
