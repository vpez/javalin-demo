package com.hackfront.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class DemoApplicationTests {

    @Value("${application.port}")
    private int port;

    @Test
    public void welcome() throws IOException, InterruptedException {
        URI uri = URI.create("http://localhost:" + port + "/welcome");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.body().contains("\"from\":\"Javalin\""));
    }

    @Test
    public void index() throws IOException, InterruptedException {
        URI uri = URI.create("http://localhost:" + port + "/");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.body().contains("<h1>Javalin Webserver Sample</h1>"));
    }
}
